package com.yq.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-16 19:28
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private int status;

}
