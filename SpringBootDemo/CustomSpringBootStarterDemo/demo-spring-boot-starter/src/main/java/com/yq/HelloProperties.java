package com.yq;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 16:28
 **/
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
