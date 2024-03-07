package com.yq;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-03-07 09:50
 **/
@SpringBootTest
public class ActivitiFlowTest {

    // Activiti 的资源管理类，该服务负责部署流程定义，管理流程资源
    @Resource
    private RepositoryService repositoryService;

    // Activiti 的流程运行管理类，用于开始一个新的流程实例，获取关于流程执行的相关信息
    @Resource
    private RuntimeService runtimeService;

    // Activiti 的任务管理类，用于处理业务运行中的各种任务，例如查询分给用户或组的任务、创建新的任务、分配任务、确定和完成一个任务
    @Resource
    private TaskService taskService;



    /**
     * 部署流程定义
     * Notice：最后通过 Activiti 可视化工具生成 xml 文件；
     * fixme： 自己创建文件并粘贴博客 xml 中的代码，无法在 act_re_procdef 流程定义表生成数据
     *         可能是文件名问题
     *         还有一种可能是没有设置流程表示，链接：https://blog.csdn.net/fengzi2563/article/details/134456949
     */
    @Test
    public void deploy(){
        Deployment deployment = repositoryService.createDeployment()       // 创建部署
                .addClasspathResource("bpmn/请假.bpmn20.xml")               // 加载流程资源文件
//                .addClasspathResource("image/process.png")               // 加载 png 文件可选
                .name("process流程")                                        // 流程名称
                .deploy();                                                 // 部署
        System.out.println("流程部署ID：" + deployment.getId());
        System.out.println("流程部署Name：" + deployment.getName());
    }

    /**
     *  Notice：调用的方法是 startProcessInstanceByKey 参数是 act_re_procdef 表中的 key 字段
     *
     *  流程实例ID：17502
     *  流程定义ID：请假:1:5004
     */
    @Test
    public void start(){
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("请假");
        System.out.println("流程实例ID：" + pi.getId());
        System.out.println("流程定义ID：" + pi.getProcessDefinitionId());
    }


    /**
     * 审批通过
     */
    @Test
    public void completeTask2(){
        taskService.complete("17506");
    }


}
