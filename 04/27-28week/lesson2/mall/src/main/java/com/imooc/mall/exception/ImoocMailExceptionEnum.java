package com.imooc.mall.exception;


/**
 * 异常枚举
 */
public enum ImoocMailExceptionEnum {
    NEED_USER_NAME(10001,"用户名不能为空"),
    NEED_PASSWORD(10002,"用户密码不能为空"),
    PASSWORD_TOO_SHORT(10003,"密码长度不能少于8位"),
    NAME_EXISTED(10004,"不允许重名"),
    INSERT_FAILED(10005,"插入失败"),
    WRONG_PASSWORD(10006,"密码错误"),
    NEED_LOGIN(10007,"用户未登录"),
    UPDATE_FAILED(10007,"更新失败"),
    NEED_ADMIN(10008,"无管理员权限"),
    NAME_NOT_NULL(10010,"名字不能为空"),
    PARA_NOT_NULL(10010,"参数不能为空"),
    CREATE_FAILED(10011,"新增失败"),
    REQUEST_PARAM_ERROR(10012,"参数错误"),
    DELETE_FAILD(10013,"删除失败"),
    MKDIR_FAILED(10014,"文件夹创建失败"),
    UPLOAD_FAILED(10014,"上传失败"),

    SYSTEM_ERROR(20000,"系统异常");


    /**
     * 异常码
     */
    Integer code;



    /**
     * 异常信息
     */
    String msg;

    ImoocMailExceptionEnum(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
