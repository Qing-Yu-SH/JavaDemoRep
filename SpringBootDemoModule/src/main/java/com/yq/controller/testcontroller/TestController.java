package com.yq.controller.testcontroller;

import com.yq.common.CommonException;
import com.yq.common.ResVo;
import com.yq.common.StatusEnum;
import com.yq.util.EmailUtil;
import com.yq.util.SpringUtil;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 13:03
 **/
@Slf4j
@ApiOperation(value = "测试接口", notes = "测试类型接口", tags = "测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FreeMarkerConfigurer configuration;

    @GetMapping("/index")
    public String index(@ApiParam(name = "name", value = "姓名", required = true, defaultValue = "", allowableValues = "Yu;A") String name){
        log.info("访问接口 {}，参数 {}","/index",name);
        return "hello," + name;
    }

    @RequestMapping("alarm")
    public ResVo<String> alarm(String content){
        content = StringEscapeUtils.escapeHtml4(content);
        log.error("测试异常报警 {}", content);
        return ResVo.ok("移除日志接收完成！");
    }

    @RequestMapping("/exp")
    public ResVo<String> exception(){
        throw CommonException.newInstance(StatusEnum.FORBID_NOTLOGIN);
    }

    @RequestMapping("/simpleEmail")
    public ResVo<String> email(){
        boolean ans = EmailUtil.sendMail("SpringBoot 异常提醒", "xx@163.com", "测试邮件，可忽略");
        return ResVo.ok(String.valueOf(ans));
    }

    @RequestMapping("/htmlEmail")
    public ResVo<String> htmlEmail() throws MessagingException {
        JavaMailSender javaMailSender = SpringUtil.getBean(JavaMailSender.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("2918148246@qq.com");
        mimeMessageHelper.setTo("yuqingzcmu@163.com");
        mimeMessageHelper.setSubject("SpringBoot HTML 测试邮件");

        // 邮件内容
        mimeMessageHelper.setText("<h1>hello world</h1> <br/>", true);

        javaMailSender.send(mimeMessage);

        return ResVo.ok(String.valueOf(true));
    }

    @RequestMapping("/withFileEmail")
    public ResVo<String> withFileEmail() throws MessagingException, MalformedURLException {
        JavaMailSender javaMailSender = SpringUtil.getBean(JavaMailSender.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("xx@qq.com");
        mimeMessageHelper.setTo("xx@163.com");
        mimeMessageHelper.setSubject("SpringBoot 附件 测试邮件");

        // 邮件内容
        mimeMessageHelper.setText("<h1>hello world</h1> <br/>", true);

        String url = "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&lid=11334049109396409410&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=979109848,1372382017&os=4154513871,1938936410&simid=4156895413,649510306&pn=25&rn=1&di=7264239678495129601&ln=1009&fr=&fmq=1696580950116_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=1e&objurl=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-07-17%2F5b4da21725ef3.jpg&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MTEsMCw2LDMsMSw0LDUsMiw4LDcsOQ%3D%3D";
        URL imageUrl = new URL(url);
        mimeMessageHelper.addAttachment("img.jpg",imageUrl::openStream);

        javaMailSender.send(mimeMessage);

        return ResVo.ok(String.valueOf(true));
    }

    @RequestMapping("/freemakerEmail")
    public ResVo<String> freemakerEmail() throws MessagingException, IOException, TemplateException {
        JavaMailSender javaMailSender = SpringUtil.getBean(JavaMailSender.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("xx@qq.com");
        mimeMessageHelper.setTo("xx@163.com");
        mimeMessageHelper.setSubject("SpringBoot HTML 测试邮件");

        Map<String, String> map = new HashMap<>();
        map.put("title","邮件标题");
        map.put("content","邮件正文");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.createConfiguration().getTemplate("ftl/email-template.ftl"), map);

        // 邮件内容
        mimeMessageHelper.setText(text, true);

        javaMailSender.send(mimeMessage);

        return ResVo.ok(String.valueOf(true));
    }

}
