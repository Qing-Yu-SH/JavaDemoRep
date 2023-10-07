package com.yq.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.yq.util.RabbitmqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 22:26
 **/
//@Component
public class RabbitmqClient {

    @Autowired
    private RabbitmqProperties rabbitmqProperties;

    /**
     * 创建一个工厂
     * @param key
     * @return
     */
    public ConnectionFactory getConnectionFactory(String key) {
        String host = rabbitmqProperties.getHost();
        Integer port = rabbitmqProperties.getPort();
        String userName = rabbitmqProperties.getUsername();
        String password = rabbitmqProperties.getPassport();
        String virtualhost = rabbitmqProperties.getVirtualhost();
        return RabbitmqUtil.getOrInitConnectionFactory(key, host, port, userName,password, virtualhost);
    }

}
