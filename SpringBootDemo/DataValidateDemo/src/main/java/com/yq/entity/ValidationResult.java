package com.yq.entity;

import lombok.Value;

/**
 * @program: JavaDemoRep
 * @description: 验证结果类
 * @author: Yuqing
 * @create: 2023-11-23 10:03
 **/
@Value
public class ValidationResult {

    private final boolean isValid;
    private final String errorMsg;

    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult invalid(String errorMsg) {
        return new ValidationResult(false, errorMsg);
    }

    public boolean notValid() {
        return !isValid;
    }

}
