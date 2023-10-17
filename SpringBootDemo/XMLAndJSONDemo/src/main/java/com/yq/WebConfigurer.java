package com.yq;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-17 23:14
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    /**
     * 当指定了 accept 时，并且传参中指定了 mediaType，则以传参为准
     * 若传参与 produce 冲突了，那么就直接 406 异常，不会选择 mediaType 设置的类型
     * 通过下面的配置，可以实现默认传 JSON
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
                // 禁用accept协商方式，即不关心前端传的accept值
                .ignoreAcceptHeader(true)
                // 哪个放在前面，哪个的优先级就高； 当上面这个accept未禁用时，若请求传的 accept 不能覆盖下面两种，则会出现406错误
                .defaultContentType(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                // 根据传参 mediaType 来决定返回样式
                .parameterName("mediaType")
                // 当 acceptHeader 未禁用时，accept 的值与 mediaType 传参的值不一致时，以 mediaType 的传值为准
                // mediaType 值可以不传，为空也行，但是不能是 json/xml 之外的其他值
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
