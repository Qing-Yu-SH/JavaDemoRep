package com.yq.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: JavaDemoRep
 * @description: 状态
 * @author: Yuqing
 * @create: 2023-10-06 14:54
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    private int code;

    private String msg;

    public static Status newStatus(int code, String msg) {
        return new Status(code, msg);
    }

    public static Status newStatus(StatusEnum status, Object... msgs) {
        String msg;
        if (msgs.length > 0) {
            msg = String.format(status.getMsg(), msgs);
        } else {
            msg = status.getMsg();
        }
        return newStatus(status.getCode(), msg);
    }

}
