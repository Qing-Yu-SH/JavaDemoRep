package com.yq.hook.handler;

import com.yq.common.CommonException;
import com.yq.common.ResVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 14:52
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public ResVo<String> handleCommonException(CommonException e){
        return ResVo.fail(e.getStatus());
    }

}
