package com.yq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: JavaDemoRep
 * @description:  切面虽然拦截了我们目标方法，但其中能拿到的信息上下文有限，无法构成一条操作日志所需的数据信息
 *                切面的定义和使用都是非业务化的，所以无法感知到新的业务操作范围和业务的定义划分边界是如何处理
 *                当业务操作是设计多表或者多个服务间的调用串联时，切面只能单独记录每个服务方法级别的数据信息，无法对调用链的部分进行业务串联
 *                改进部分看项目：LogAspectDemo2
 * @author: Yuqing
 * @create: 2023-10-18 10:23
 **/
@SpringBootApplication
public class LogAspectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogAspectApplication.class);
    }

}
