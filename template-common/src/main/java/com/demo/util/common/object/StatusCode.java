package com.demo.util.common.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dp
 * @date 2020/11/2 9:01 下午
 */
@AllArgsConstructor
public enum StatusCode implements Status {

    SERVICE_RUN_SUCCESS(10000, "服务运行成功");

    private static final Integer SUCCESS_CODE = 10000;

    @Getter
    @Setter
    private int status;

    @Setter
    @Getter
    private String msg;

    @Override
    public boolean isSuccess() {
        return getStatus() == SUCCESS_CODE;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMsg() {
        return String.format(msg, "");
    }

    @Override
    public String getMsg(Object... objects) {
        if (objects == null) {
            return getMsg();
        }

        return String.format(msg, objects);
    }
}
