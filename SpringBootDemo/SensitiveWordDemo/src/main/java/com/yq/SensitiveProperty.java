package com.yq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 18:17
 **/
@Data
@Component
@ConfigurationProperties(prefix = "sw.sensitive")  // 将外部配置属性绑定到 Java 对象的属性上
public class SensitiveProperty {

    /**
     * true 表示开启敏感词校验
     */
    private Boolean enable;

    private List<String> deny;

    private List<String> allow;

}
