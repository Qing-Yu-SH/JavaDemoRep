package com.yq.dto;

import lombok.Data;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 21:56
 **/
@Data
public class PageDto {

    private Integer page;
    private Integer limit;
    private Long count;

}
