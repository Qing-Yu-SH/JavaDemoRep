package com.yq.mapper;

import com.yq.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-08 19:53
 **/
@Mapper
public interface ArticleMapper {

    Article getArticleById(Integer id);

}
