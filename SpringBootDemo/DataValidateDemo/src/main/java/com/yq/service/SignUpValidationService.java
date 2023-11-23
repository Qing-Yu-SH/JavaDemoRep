package com.yq.service;

import com.yq.entity.UserSignUpCommand;
import com.yq.entity.ValidationResult;

/**
 * @program: JavaDemoRep
 * @description: 注册校验服务
 * @author: Yuqing
 * @create: 2023-11-23 10:10
 **/
public interface SignUpValidationService {

    ValidationResult validate(UserSignUpCommand command);

}
