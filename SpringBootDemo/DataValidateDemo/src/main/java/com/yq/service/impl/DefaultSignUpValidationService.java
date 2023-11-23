package com.yq.service.impl;

import com.yq.entity.UserSignUpCommand;
import com.yq.entity.ValidationResult;
import com.yq.service.SignUpValidationService;
import com.yq.util.validation.ValidationStep;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-23 10:11
 **/
@Service
public class DefaultSignUpValidationService implements SignUpValidationService {

    @Resource(name = "authorizationInformationHelper")
    private ValidationStep<UserSignUpCommand> userSignUpValidationHelper;

    @Override
    public ValidationResult validate(UserSignUpCommand command) {
        return userSignUpValidationHelper.validate(command);
    }



}
