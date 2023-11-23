package com.yq.controller;

import com.yq.entity.UserSignUpCommand;
import com.yq.entity.ValidationResult;
import com.yq.service.SignUpValidationService;
import com.yq.util.bloom.EmailBloomFilter;
import com.yq.util.bloom.UserNameBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-23 09:52
 **/
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private SignUpValidationService signUpValidationService;

    @PostMapping("/register")
    public ValidationResult register(@RequestBody UserSignUpCommand command){
        ValidationResult validationResult = signUpValidationService.validate(command);
        if(validationResult.notValid()){
            return validationResult;
        }

        UserNameBloomFilter.add(command.getUserName());
        EmailBloomFilter.add(command.getEmail());
        return validationResult;
    }

}
