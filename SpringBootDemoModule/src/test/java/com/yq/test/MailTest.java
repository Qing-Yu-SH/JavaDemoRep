package com.yq.test;


import com.yq.util.EmailUtil;
import org.junit.Test;

import javax.mail.MessagingException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 15:30
 **/
public class MailTest extends BasicTest{


    @Test
    public void test_mail() throws MessagingException {
        EmailUtil.sendMail("SpringBoot 异常提醒","yuqingzcmu@163.com","测试邮件，可忽略");
    }

}
