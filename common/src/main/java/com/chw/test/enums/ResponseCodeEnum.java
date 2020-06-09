package com.chw.test.enums;

public enum ResponseCodeEnum {

    Login_Error(10010,"登录失败"),
    Not_Login(10020,"当前用户未登录"),
    No_Authority(10030,"暂无权限");

    private Integer code;

    private String msg;

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
