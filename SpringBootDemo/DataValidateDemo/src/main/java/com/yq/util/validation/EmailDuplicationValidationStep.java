package com.yq.util.validation;

import com.yq.entity.UserSignUpCommand;
import com.yq.entity.ValidationResult;
import com.yq.util.bloom.EmailBloomFilter;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-23 19:00
 **/
public class EmailDuplicationValidationStep extends ValidationStep<UserSignUpCommand>{


    @Override
    public ValidationResult validate(UserSignUpCommand command) {

        if(EmailBloomFilter.contains(command.getEmail())){
            return ValidationResult.invalid(String.format("Email [%s] is already taken.", command.getEmail()));
        }

        return checkNext(command);
    }

}
