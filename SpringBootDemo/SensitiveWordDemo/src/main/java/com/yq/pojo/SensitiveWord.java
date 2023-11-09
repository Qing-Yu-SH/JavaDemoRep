package com.yq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 19:27
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SensitiveWord {

    private Integer id;
    private String sensitives;
    private Date createTime;

}
