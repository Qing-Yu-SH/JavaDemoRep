package com.yq.rabbitmq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: JavaDemoRep
 * @description: RabbitMQ 连接池
 * @author: Yuqing
 * @create: 2023-10-06 22:50
 **/
public class RabbitmqConnectionPool {

    private static BlockingQueue<RabbitmqConnection> pool;

    public static void initRabbitmqConnectionPool(String host, int port, String userName, String password,
                                                  String virtualhost,
                                                  Integer poolSize) {
        pool = new LinkedBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            pool.add(new RabbitmqConnection(host, port, userName, password, virtualhost));
        }
    }

    public static RabbitmqConnection getConnection() throws InterruptedException {
        return pool.take();
    }

    public static void returnConnection(RabbitmqConnection connection) {
        pool.add(connection);
    }

    public static void close() {
        pool.forEach(RabbitmqConnection::close);
    }

}
