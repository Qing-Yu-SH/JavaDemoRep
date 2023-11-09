package com.yq;

import com.yq.mapper.SensitiveWordMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 19:33
 **/
@SpringBootTest(classes = SensitiveWordApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestProject {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    @Autowired
    private SensitiveService sensitiveService;

    @Test
    public void test_dataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void test_mybatis(){
        List<String> allDenyWord = sensitiveWordMapper.getAllDenyWord();
        allDenyWord.stream().forEach(System.out::println);
    }

    @Test
    public void test_sensitiveService(){
        String txt = "我靠，出售身份信息。同时出售身份证，卖套牌车。信用卡提现，私人侦探等等";
        System.out.println(sensitiveService.contains(txt));
        List<String> words = sensitiveService.findAll(txt);
        for(String word: words){
            System.out.print(word + " ");
        }
        System.out.println();
        String replace = sensitiveService.replace(txt);
        System.out.println(replace);
    }

}
