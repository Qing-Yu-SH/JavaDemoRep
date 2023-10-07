package com.yq.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 22:22
 **/
@Setter
@Getter
//@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqProperties {

    private String host;

    private Integer port;

    private String username;

    private String passport;

    private String virtualhost;

    private Boolean switchFlag;

    private Integer poolSize;

}
