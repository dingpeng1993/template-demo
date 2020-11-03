package com.demo.util.common.object.response;

import com.demo.util.common.object.StatusCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dp
 * @date 2020/11/3 9:35 上午
 */
public class BaseResponse<T> extends Response<T> {

    public BaseResponse() {
    }

    public BaseResponse(T data) {
        setStatus(StatusCode.SERVICE_RUN_SUCCESS.getStatus());
        setMsg(StatusCode.SERVICE_RUN_SUCCESS.name());
        setData(data);
    }

    public BaseResponse(int status, String msg) {
        setSuccess(status == StatusCode.SERVICE_RUN_SUCCESS.getStatus());
        setStatus(status);
        setMsg(msg);
    }

    public BaseResponse(StatusCode statusCode, String msg) {
        if (statusCode != StatusCode.SERVICE_RUN_SUCCESS) {
            setSuccess(false);
        }
        setStatus(statusCode.getStatus());
        setMsg(msg);
    }

    public BaseResponse(int status, String code, String msg) {
        setSuccess(status == StatusCode.SERVICE_RUN_SUCCESS.getStatus());
        setStatus(status);
        setCode(code);
        setMsg(msg);
    }

    public static <T> BaseResponse<T> create(StatusCode statusCode, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(statusCode == StatusCode.SERVICE_RUN_SUCCESS);
        response.setStatus(statusCode.getStatus());
        response.setCode(statusCode.getCode());
        response.setMsg(statusCode.name());
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> success(T data) {
        return create(StatusCode.SERVICE_RUN_SUCCESS, data);
    }

    public static <E> BaseResponse successList(List<E> items) {
        Map<String, List<E>> map = new HashMap<>(16);
        map.put("items", items);
        return create(StatusCode.SERVICE_RUN_SUCCESS, map);
    }

    public static <T> BaseResponse ok() {
        return new BaseResponse(null);
    }
}
