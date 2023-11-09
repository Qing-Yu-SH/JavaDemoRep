package com.yq.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 19:28
 **/
@Mapper
public interface SensitiveWordMapper {

    List<String> getAllDenyWord();

}
