package com.demo.util.common.exception;

import com.demo.util.common.object.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dp
 * @date 2020/3/15 2:35 下午
 */
@AllArgsConstructor
@Getter
public class CommonException extends RuntimeException {
    /**
     * 状态码
     */
    private int status;
    /**
     * code码
     */
    private String code;
    /**
     * 消息
     */
    private String msg;

    public CommonException(Status status, String... format) {
        this(status.getStatus(), status.getCode(), status.getMsg());
    }

    public CommonException(Status status) {
        this(status.getStatus(), status.getCode(), status.getMsg());
    }

    public CommonException(Exception e) {
        super(e);
    }

}
