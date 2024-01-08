package com.yq.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-08 12:05
 **/
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    private Integer id;
    private String username;
    private String password;

}
