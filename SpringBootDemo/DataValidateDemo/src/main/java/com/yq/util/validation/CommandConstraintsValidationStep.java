package com.yq.util.validation;

import com.yq.entity.UserSignUpCommand;
import com.yq.entity.ValidationResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-23 10:14
 **/
public class CommandConstraintsValidationStep extends ValidationStep<UserSignUpCommand>{

    @Override
    public ValidationResult validate(UserSignUpCommand command) {
        // 通过 javax.validation 进行参数校验
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()){
            final Validator validator = validatorFactory.getValidator();
            final Set<ConstraintViolation<UserSignUpCommand>> constraintViolations = validator.validate(command);

            if(!constraintViolations.isEmpty()){
                return ValidationResult.invalid(constraintViolations.iterator().next().getMessage());
            }
        }

        return checkNext(command);
    }

}
