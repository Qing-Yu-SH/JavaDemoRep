package com.yq.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-19 21:23
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReqVo {

    private Timestamp timestamp;
    private String meterId;
    private Double value;

}
