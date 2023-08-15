package com.yq.pattern.chain;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-15 13:24
 **/

import com.alibaba.fastjson.JSON;
import com.yq.pattern.chain.impl.Level01AuthLink;
import com.yq.pattern.chain.impl.Level02AuthLink;
import com.yq.pattern.chain.impl.Level03AuthLink;
import com.yq.pattern.chain.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * 责任链模式
 * 责任链模式的核⼼是解决⼀组服务中的先后执⾏处理关系
 *
 *
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ParseException {
        // 注意定义的过程 -- A.append(B.append(C))
        AuthLink authLink = new Level03AuthLink("10012","李工")
                .append(new Level02AuthLink("10022","王经理")
                        .append(new Level01AuthLink("10032","俞总")));


        logger.info("测试结果：{}", "模拟三级负责⼈审批，李⼯");
        logger.info(JSON.toJSON(authLink.doAuth("10002","O-1001",new Date())).toString());

        logger.info("测试结果：{}", "模拟二级负责⼈审批，王经理");
        AuthService.auth("10012","O-1001");
        logger.info(JSON.toJSON(authLink.doAuth("10002","O-1001",new Date())).toString());

        logger.info("测试结果：{}", "模拟一级负责⼈审批，俞总");
        AuthService.auth("10022","O-1001");
        logger.info(JSON.toJSON(authLink.doAuth("10002","O-1001",new Date())).toString());

        AuthService.auth("10032","O-1001");
        logger.info(JSON.toJSON(authLink.doAuth("10002","O-1001",new Date())).toString());
    }

}
