package com.yq.pattern.facade;

import com.alibaba.fastjson.JSON;
import com.yq.pattern.facade.annotation.DoDoor;
import com.yq.pattern.facade.config.StarterService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.security.Signature;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-13 19:17
 **/
// @Aspect
// @Component
public class DoJoinPoint {

    private Logger logger = LoggerFactory.getLogger(DoJoinPoint.class);

//    @Autowired
    private StarterService starterService;

//    @Pointcut("@annotation(com.yq.pattern.facade.annotation.DoDoor)")
    public void aopPoint() {
    }

//    @Around("aopPoint()")
//    public Object doRouter(ProceedingJoinPoint jp) throws Throwable{
//        //获取内容
//        Method method = getMethod(jp);
//        DoDoor door = method.getAnnotation(DoDoor.class);
//        //获取字段值
//        String keyValue = getFiledValue(door.key(), jp.getArgs());
//        logger.info("itstack door handler method：{} value：{}",
//                method.getName(), keyValue);
//        if (null == keyValue || "".equals(keyValue)) return jp.proceed();
//        //配置内容
//        String[] split = starterService.split(",");
//        //⽩名单过滤
//        for (String str : split) {
//            if (keyValue.equals(str)) {
//                return jp.proceed();
//            }
//        }
//        //拦截
//        return returnObject(door, method);
//    }

//    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
//        Signature sig = jp.getSignature();
//        MethodSignature methodSignature = (MethodSignature) sig;
//        return getClass(jp).getMethod(methodSignature.getName(),
//                methodSignature.getParameterTypes());
//    }

//    private Class<? extends Object> getClass(JoinPoint jp) throws
//            NoSuchMethodException {
//        return jp.getTarget().getClass();
//    }

    private Object returnObject(DoDoor doGate, Method method) throws IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();
        String returnJson = doGate.returnJSON();
        if ("".equals(returnJson)) {
            return returnType.newInstance();
        }
        return JSON.parseObject(returnJson, returnType);
    }

    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }


}
