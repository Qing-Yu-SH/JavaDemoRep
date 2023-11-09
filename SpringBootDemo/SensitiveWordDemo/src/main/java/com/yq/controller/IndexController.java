package com.yq.controller;

import com.yq.ArticleService;
import com.yq.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-08 19:59
 **/
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/getArticle/{id}")
    public Article getArticleById(@PathVariable("id") Integer id){
        return articleService.getArticleById(id);
    }

}
