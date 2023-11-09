package com.yq.pojo;

import com.yq.sensitive.SensitiveField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-08 19:51
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {

    private Integer id;
    private Integer articleId;
    private String title;
    @SensitiveField
    private String content;
    private Date createTime;
    private Date updateTime;

}
