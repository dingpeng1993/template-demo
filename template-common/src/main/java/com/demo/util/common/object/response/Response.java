package com.demo.util.common.object.response;

import com.demo.util.common.object.Status;
import lombok.Data;
import java.io.Serializable;

/**
 * @author dp
 * @date 2020/11/2 8:56 下午
 */
@Data
public class Response<T> implements Serializable {

    /** Define long serialVersionUID */
    private static final long serialVersionUID = 3928402875296079229L;
    /** 状态 */
    private boolean success = true;
    /** 状态码 例如:0-正常；-1-缺少参数；-3-接口异常 */
    private int status;
    /** 错误码 */
    private String code;
    /** 状态消息 */
    private String msg;
    /** 信息 */
    private T data;

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Response create(Status status) {
        return create(status.isSuccess(), status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Response create(boolean success, int status, String errorCode, String message) {
        Response response = new Response();
        response.setSuccess(success);
        response.setStatus(status);
        response.setCode(errorCode);
        response.setMsg(message);

        return response;
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Response create(Status status, String... messages) {
        return create(status.isSuccess(), status.getStatus(), status.getCode(), status.getMsg(), messages);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Response create(boolean success, int status, String errorCode, String message, String... messages) {
        Response result = new Response();

        result.setSuccess(success);
        result.setStatus(status);
        result.setCode(errorCode);
        if (message != null && message.contains("%s")) {
            if (messages != null) {
                message = String.format(message, (Object[]) messages);
            }
            // 无替换文本
            else {
                message = message.replaceAll("%s", "");
            }
        }
        result.setMsg(message);

        return result;
    }
}
