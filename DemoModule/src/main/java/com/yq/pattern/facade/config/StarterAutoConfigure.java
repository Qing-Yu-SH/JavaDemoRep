package com.yq.pattern.facade.config;

/**
 * @program: JavaDemoRep
 * @description: ⾃定义配置类信息获取
 * @author: Yuqing
 * @create: 2023-08-13 19:11
 **/
// @Configuration
// @ConditionalOnClass(StarterService.class)
// @EnableConfigurationProperties(StarterServiceProperties.class)
public class StarterAutoConfigure {

    // @Autowired
    private StarterServiceProperties properties;

//    @Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = "yq.door", value = "enabled", havingValue = "true")
    StarterService starterService() {
        return new StarterService(properties.getUserStr());
    }

}
