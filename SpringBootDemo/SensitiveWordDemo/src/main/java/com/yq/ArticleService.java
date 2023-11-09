package com.yq;

import com.yq.mapper.ArticleMapper;
import com.yq.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-08 19:58
 **/
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public Article getArticleById(Integer id){
        return articleMapper.getArticleById(id);
    }

}
