package com.yq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-26 16:26
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String name;
    private Integer age;
    private String sex;
    private String phone;
    private String address;

}
