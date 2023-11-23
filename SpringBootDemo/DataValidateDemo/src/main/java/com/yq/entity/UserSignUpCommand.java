package com.yq.entity;

import lombok.Value;

import javax.validation.constraints.*;

/**
 * @program: JavaDemoRep
 * @description:  接收用户的注册信息
 * @author: Yuqing
 * @create: 2023-11-23 09:52
 **/

@Value
public class UserSignUpCommand {

    @NotBlank
    private final String userName;

    @Size(min = 6,max = 20)
    @NotBlank
    private final String password;

    @Size(max = 60)
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号码范围不正确")
    private final String phone;

    @NotBlank
    @Pattern(regexp = "^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$", message = "ip格式不正确")
    private String ip; // 服务器ip

}
