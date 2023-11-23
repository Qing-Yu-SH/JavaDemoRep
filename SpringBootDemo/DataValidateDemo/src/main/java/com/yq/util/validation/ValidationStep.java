package com.yq.util.validation;

import com.yq.entity.ValidationResult;

/**
 * @program: JavaDemoRep
 * @description: 责任链的链类
 * @author: Yuqing
 * @create: 2023-11-23 10:04
 **/
public abstract class ValidationStep<T> {

    private ValidationStep<T> next;

    public ValidationStep<T> linkWith(ValidationStep<T> next) {
        if (this.next == null) {
            this.next = next;
            return this;
        }
        ValidationStep<T> lastStep = this.next;
        while (lastStep.next != null) {
            lastStep = lastStep.next;
        }
        lastStep.next = next;
        return this;
    }

    public abstract ValidationResult validate(T toValidate);

    protected ValidationResult checkNext(T toValidate) {
        if (next == null) {
            return ValidationResult.valid();
        }

        return next.validate(toValidate);
    }

}
