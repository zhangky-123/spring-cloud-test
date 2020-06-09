package com.chw.test.utils.jwt;

import io.jsonwebtoken.Claims;

public class CheckResult {

    private Boolean success;

    private Claims claims;

    private Integer errCode;

    private String errMsg;

    public CheckResult() {
        this.errCode =0;
        this.errMsg="";
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
