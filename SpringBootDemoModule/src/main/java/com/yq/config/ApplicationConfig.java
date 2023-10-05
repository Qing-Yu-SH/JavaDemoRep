package com.yq.config;

import com.yq.hook.filter.ZFilter;
import com.yq.hook.servlet.HealthServlet2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 13:27
 **/
@Configuration
public class ApplicationConfig {

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

}
