package com.imooc.mall.common;


import com.imooc.mall.exception.ImoocMailExceptionEnum;

/**
 * 通用返回对象
 */
public class ApiRestResponse<T> {


    private static final int OK_CODE = 10000;
    private static final String OK_MSG = "SUCCESS";
    private final Integer status;
    private final String msg;
    private T data;

    public ApiRestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiRestResponse() {
        this(OK_CODE, OK_MSG);
    }

    public static <T> ApiRestResponse<T> success() {
        return new ApiRestResponse<>();
    }

    public static <T> ApiRestResponse<T> success(T result) {
        ApiRestResponse<T> response = new ApiRestResponse<>();
        response.setData(result);
        return response;
    }

    public static <T> ApiRestResponse<T> error(Integer code, String msg) {
        return new ApiRestResponse<>(code, msg);
    }
    public static <T> ApiRestResponse<T> error(ImoocMailExceptionEnum ex) {
        return new ApiRestResponse<>(ex.getCode(), ex.getMsg());
    }

    @Override
    public String toString() {
        return "ApiRestResponse{"+
                "status="+status+
                ".msg='"+msg+'\''+
                ",msg='"+msg+'\''+
                ",data="+data+
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
