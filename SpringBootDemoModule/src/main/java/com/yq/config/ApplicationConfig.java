package com.yq.config;

import com.yq.hook.filter.ZFilter;
import com.yq.hook.servlet.HealthServlet2;
import com.yq.util.RedisClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 13:27
 **/
@Configuration
public class ApplicationConfig {

    // 通过 Spring Boot 的自动配置机制进行自动注入；Spring Boot 会根据配置，自动创建一个 RedisTemplate 实例，并注入构造方法
    public ApplicationConfig(RedisTemplate<String, String> redisTemplate) {
       RedisClient.register(redisTemplate);
    }


    // 注册 Filter 方式 2
    @Bean
    public FilterRegistrationBean<Filter> orderFilter(){
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setName("zFilter");
        filter.setUrlPatterns(Arrays.asList("/*"));
        filter.setFilter(new ZFilter());
        // 指定优先级
        filter.setOrder(2);
        return filter;
    }

    // 注册 Servlet 方式 2
    @Bean
    public ServletRegistrationBean servletBean(){
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.addUrlMappings("/check2");
        registrationBean.setServlet(new HealthServlet2());
        return registrationBean;
    }

    @Bean(name = "freeMarkerConfigurer")
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setDefaultEncoding("UTF-8");
        configurer.setTemplateLoaderPath("classpath:");
        Map<String, Object> variables = new HashMap<>(1<<1);
        variables.put("xml_escape","fmXmlEscape");
        configurer.setFreemarkerVariables(variables);
        return configurer;
    }


}
