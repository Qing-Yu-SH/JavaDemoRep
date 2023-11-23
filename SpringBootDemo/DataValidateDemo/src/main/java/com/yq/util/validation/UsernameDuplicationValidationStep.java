package com.yq.util.validation;

import com.yq.entity.UserSignUpCommand;
import com.yq.entity.ValidationResult;
import com.yq.util.bloom.UserNameBloomFilter;

/**
 * @program: JavaDemoRep
 * @description: 用户名是否重名校验
 * @author: Yuqing
 * @create: 2023-11-23 10:18
 **/
public class UsernameDuplicationValidationStep extends ValidationStep<UserSignUpCommand>{

    @Override
    public ValidationResult validate(UserSignUpCommand command) {

        if(UserNameBloomFilter.contains(command.getUserName())){
            return ValidationResult.invalid(String.format("Username [%s] is already taken.", command.getUserName()));
        }

        return checkNext(command);
    }

}
