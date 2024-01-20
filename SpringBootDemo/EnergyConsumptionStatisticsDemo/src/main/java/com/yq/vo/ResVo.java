package com.yq.vo;

import com.yq.vo.constants.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-19 21:17
 **/
@Getter
@Setter
public class ResVo<T> implements Serializable {

    private static final long serialVersionUID = -510306209659393854L;

    private Status status;

    private T result;

    public ResVo() {
    }

    public ResVo(Status status) {
        this.status = status;
    }

    public ResVo(T t) {
        status = Status.newStatus(StatusEnum.SUCCESS);
        this.result = t;
    }

    public static <T> ResVo<T> ok(T t) {
        return new ResVo<T>(t);
    }

    public static <T> ResVo<T> fail(StatusEnum status, Object... args) {
        return new ResVo<>(Status.newStatus(status, args));
    }

    public static <T> ResVo<T> fail(Status status) {
        return new ResVo<>(status);
    }

}
