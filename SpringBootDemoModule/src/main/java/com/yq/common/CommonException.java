package com.yq.common;

import lombok.Data;

/**
 * @program: JavaDemoRep
 * @description: 异常类
 * @author: Yuqing
 * @create: 2023-10-06 14:32
 **/
@Data
public class CommonException extends RuntimeException{

    private Status status;


    public static CommonException newInstance(StatusEnum statusEnum){
        Status status = Status.newStatus(statusEnum);
        return new CommonException(status);
    }

    private CommonException(Status status){
        this.status = status;
    }

}
