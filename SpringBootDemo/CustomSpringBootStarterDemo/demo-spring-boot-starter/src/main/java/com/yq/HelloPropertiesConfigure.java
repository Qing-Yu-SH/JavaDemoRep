package com.yq;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 15:38
 **/
// 3.自动装配
@Configuration
@EnableConfigurationProperties(HelloProperties.class)      // 启用 HelloProperties 类中使用 @ConfigurationProperties 注解的配置属性
public class HelloPropertiesConfigure {
}
