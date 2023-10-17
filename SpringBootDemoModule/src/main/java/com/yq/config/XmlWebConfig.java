package com.yq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 注册xml解析器
 *               WebMvcConfigurer 用于配置和定制 Spring MVC 框架的行为；可以自定义请求映射、视图解析、拦截器、消息转换器等 Spring MVC 的各种特性
 * @author: Yuqing
 * @create: 2023-10-07 15:45
 **/
@Configuration
public class XmlWebConfig implements WebMvcConfigurer {

    /**
     * 用于配置消息转换器（Message Converter）以支持 JSON 和 XML 格式的数据交换
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }

    /**
     * 返回值类型匹配
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
                .defaultContentType(MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN, MediaType.TEXT_EVENT_STREAM, MediaType.APPLICATION_OCTET_STREAM, MediaType.MULTIPART_FORM_DATA, MediaType.MULTIPART_MIXED, MediaType.MULTIPART_RELATED)
                // 当下面的配置为 false（默认值）时，通过浏览器访问后端接口，会根据 acceptHeader 协商进行返回，返回结果都是 xml 格式；与我们日常习惯不太匹配
                // 因此禁用请求头的 AcceptHeader，在需要进行 xml 交互的接口上，手动加上 consumer, produces 属性； 因为本项目中，只有微信的交互是采用的xml进行传参、返回，其他的是通过json进行交互，所以只在微信的 WxRestController 中需要特殊处理；其他的默认即可
                .ignoreAcceptHeader(true)
                .parameterName("mediaType")
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("text", MediaType.TEXT_PLAIN)
                .mediaType("text/event-stream", MediaType.TEXT_EVENT_STREAM)
                .mediaType("application/octet-stream", MediaType.APPLICATION_OCTET_STREAM)
                .mediaType("multipart/form-data", MediaType.MULTIPART_FORM_DATA);
    }

}
