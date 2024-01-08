package com.yq;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-08 12:38
 **/
@Slf4j
@Aspect
@Component
public class LogAopAspect implements ApplicationContextAware {

    private SpelExpressionParser parser;
    private ParameterNameDiscoverer parameterNameDiscoverer;
    private BeanFactoryResolver beanFactoryResolver;

    private ApplicationContext applicationContext;

    public LogAopAspect(){
        parser = new SpelExpressionParser();
        parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        beanFactoryResolver = new BeanFactoryResolver(applicationContext);
    }

    @Around("@annotation(logRecord)")
    public Object around(ProceedingJoinPoint joinPoint, LogRecord logRecord) throws Throwable {
        long start = System.currentTimeMillis();

        Map<String, Object> logResult = parseLogRecord(joinPoint, logRecord);
        try {
            return joinPoint.proceed();
        }finally {
            long cost = System.currentTimeMillis() - start;
            String info = logConvert(logResult);
            log.info("\ninfo：\n{}cost：{}",info,cost);
        }
    }

    public Map<String,Object> parseLogRecord(ProceedingJoinPoint joinPoint, LogRecord logRecordAnnotation){
        Map<String,Object> result = new HashMap<>();

        if(logRecordAnnotation == null){
            return result;
        }

        StandardEvaluationContext context = new StandardEvaluationContext();
        // 允许 SpEL 表达式引用 Spring 上下文中的 bean
        context.setBeanResolver(beanFactoryResolver);

        // 获取参数名称
        String[] params = parameterNameDiscoverer.getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(params[i], args[i]);
        }

        for (Method method: LogRecord.class.getDeclaredMethods()){
            try {
                Object value = method.invoke(logRecordAnnotation);
                if(value != null){
                    // 参照 @Value 注解中 SpEL表达式 的写法；@Value SpEL表达式 处理核心方法 StandardBeanExpressionResolver#evaluate
                    // 需调用 TemplateAwareExpressionParser#parseExpression(String expressionString, @Nullable ParserContext context) 才能正常解析
                    result.put(method.getName(), parseException(value.toString(),context));
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private String logConvert(Map<String,Object> result){
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String,Object> entry : result.entrySet()){
            Object value = entry.getValue();
            if(value==null || (value instanceof String && StrUtil.isEmpty(value.toString()))){
                continue;
            }
            builder.append(entry.getKey()).append("：").append(JSON.toJSON(value)).append("; \n");
        }
        return builder.toString();
    }

    private String parseException(String expressionString,StandardEvaluationContext context){
        StringBuilder builder = new StringBuilder();
        String prefix = "#{";
        String suffix = "}";
        int startIdx = 0;
        while(startIdx < expressionString.length()) {
            int prefixIndex = expressionString.indexOf(prefix, startIdx);
            if (prefixIndex >= startIdx) {
                if (prefixIndex > startIdx) {
                    builder.append(expressionString.substring(startIdx, prefixIndex));
                }

                int afterPrefixIndex = prefixIndex + prefix.length();
                int suffixIndex = expressionString.indexOf(suffix, afterPrefixIndex);
                if(suffixIndex == -1){
                    throw new ParseException(expressionString, prefixIndex, "No ending suffix '" + suffix + "' for expression starting at character " + prefixIndex + ": " + expressionString.substring(prefixIndex));
                }
                String expr = expressionString.substring(prefixIndex + prefix.length(), suffixIndex);
                builder.append(parser.parseExpression("#"+expr).getValue(context));
                startIdx = suffixIndex + suffix.length();
            }else {
                builder.append(expressionString.substring(startIdx));
                startIdx = expressionString.length();
            }
        }
        return builder.toString();
    }

}
