package com.yq.config;

import com.yq.entity.UserSignUpCommand;
import com.yq.util.validation.CommandConstraintsValidationStep;
import com.yq.util.validation.EmailDuplicationValidationStep;
import com.yq.util.validation.UsernameDuplicationValidationStep;
import com.yq.util.validation.ValidationStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-23 19:10
 **/
@Configuration
public class ValidateLinkConfig {

    @Bean("authorizationInformationHelper")
    public ValidationStep<UserSignUpCommand> validationStep(){
        return new CommandConstraintsValidationStep()
                .linkWith(new UsernameDuplicationValidationStep())
                .linkWith(new EmailDuplicationValidationStep());
    }

}
