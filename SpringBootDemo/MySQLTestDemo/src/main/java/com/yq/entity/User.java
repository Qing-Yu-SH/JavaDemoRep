package com.yq.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-01 14:57
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private Integer id;
    private String userName;
    private String password;
    private Integer age;
    private String phone;
    private Date createTime;

}
