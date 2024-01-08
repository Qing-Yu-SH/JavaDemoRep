package com.yq;

import com.yq.service.User;
import com.yq.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-08 10:03
 **/
@SpringBootTest
public class TestUnit {

    @Autowired
    private UserService userService;

    @Value("#{user2.username}")
    private String name;

    @Test
    public void test_Annotation(){
        for (Method method: LogRecord.class.getDeclaredMethods()){
            System.out.println(method.getName());
        }
    }

    @Test
    public void test_SpEL(){
        // 解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 定义 SpEL 表达式
        Expression expression = parser.parseExpression("'Hello '.concat(#param)");

        // 创建一个 EvaluationContext，用于提供变量值
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("param", "World");

        System.out.println(expression.getValue(context));
    }

    @Test
    public void test_SpEL2(){
        // 解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 定义 SpEL 表达式
        Expression expression = parser.parseExpression("#user.id");

        // 创建一个 EvaluationContext，用于提供变量值
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", new User(1,"sumAll","123456"));

        System.out.println(expression.getValue(context));
    }

    @Test
    public void test_SpelExpressionParser(){
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("用户：#{user.id} 信息变更",new TemplateParserContext());
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", new User(1,"sumAll","123456"));
        System.out.println(expression.getValue(context));
    }

    @Test
    public void test_Method(){
        userService.updatePassword(new User(1,"sumAll","123456"));
    }

    @Test
    public void test_name(){
        System.out.println(name);
    }

}
