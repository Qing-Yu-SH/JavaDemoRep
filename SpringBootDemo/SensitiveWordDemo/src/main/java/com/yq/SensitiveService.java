package com.yq;

import cn.hutool.core.util.BooleanUtil;
import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllowSystem;
import com.github.houbb.sensitive.word.support.deny.WordDenySystem;
import com.yq.mapper.SensitiveWordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 18:34
 **/
@Slf4j
@Service
public class SensitiveService {

    @Autowired
    private SensitiveProperty sensitiveProperty;

    private volatile SensitiveWordBs sensitiveWordBs;


    @PostConstruct
    public void refresh(){


        IWordDeny deny = () -> {
            List<String> sub = WordDenySystem.getInstance().deny();
            sub.addAll(sensitiveProperty.getDeny());
            return sub;
        };

        IWordAllow allow = () -> {
            List<String> sub = WordAllowSystem.getInstance().allow();
            sub.addAll(sensitiveProperty.getAllow());
            return sub;
        };

        sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordDeny(deny)
                .wordAllow(allow)
                .init();
        log.info("敏感词初始化完成..");
    }

    /**
     * 判断是否存在敏感词
     * @param txt
     * @return
     */
    public boolean contains(String txt){
        if(BooleanUtil.isTrue(sensitiveProperty.getEnable())){
            return sensitiveWordBs.contains(txt);
        }
        return false;
    }

    /**
     * 敏感词替换
     * @param txt
     * @return
     */
    public String replace(String txt){
        if(BooleanUtil.isTrue(sensitiveProperty.getEnable())){
            return sensitiveWordBs.replace(txt);
        }
        return txt;
    }

    /**
     * 查询文本中所有命中的敏感词
     * @param txt
     * @return
     */
    public List<String> findAll(String txt){
        return sensitiveWordBs.findAll(txt);
    }



}
