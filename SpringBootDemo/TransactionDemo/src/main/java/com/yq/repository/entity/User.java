package com.yq.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 13:14
 **/
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private Integer height;

    public User(String name, Integer age, String sex, Integer height) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
    }

}
