package com.xyx.entity.response;

import com.xyx.constants.CommonConstrants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AppResponse <T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @ApiModelProperty(value = "返回标记：成功=200，失败=500")
    private int code;

    @Getter
    @Setter
    @ApiModelProperty(value = "返回信息")
    private String msg;


    @Setter
    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> AppResponse<T> ok() {
        return restResult(null, CommonConstrants.SUCCESS, null);
    }

    public static <T> AppResponse<T> ok(T data) {
        return restResult(data, CommonConstrants.SUCCESS, null);
    }

    public static <T> AppResponse<T> success(T data) {
        return restResult(data, CommonConstrants.SUCCESS, null);
    }

    public static AppResponse success() {
        return restResult(null, CommonConstrants.SUCCESS, null);
    }

    public static <T> AppResponse<T> ok(T data, String msg) {
        return restResult(data, CommonConstrants.SUCCESS, msg);
    }

    public static <T> AppResponse<T> failed() {
        return restResult(null, CommonConstrants.FAIL, null);
    }

    public static <T> AppResponse<T> error() {
        return restResult(null, CommonConstrants.FAIL, null);
    }

    public static <T> AppResponse<T> failed(String msg) {
        return restResult(null, CommonConstrants.FAIL, msg);
    }

    public static <T> AppResponse<T> error(String msg) {
        return restResult(null, CommonConstrants.FAIL, msg);
    }

    public static <T> AppResponse<T> error(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> AppResponse<T> failed(T data) {
        return restResult(data, CommonConstrants.FAIL, null);
    }

    public static <T> AppResponse<T> failed(T data, String msg) {
        return restResult(data, CommonConstrants.FAIL, msg);
    }

    public static <T> AppResponse<T> failed(T data, int code, String msg) {
        return restResult(data, code, msg);
    }

    public static <T> AppResponse<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> AppResponse<T> restResult(T data, int code, String msg) {
        AppResponse<T> response = new AppResponse<>();
        response.setCode(code);
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public T getData() {
        return data;
    }

    public boolean isOk() {
        if (code != 200) {
            return false;
        } else {
            return true;
        }
    }
}
