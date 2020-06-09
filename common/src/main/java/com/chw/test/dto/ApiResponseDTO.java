package com.chw.test.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponseDTO implements Serializable {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;


    public ApiResponseDTO() {
        this.code = 0;
        this.msg = "ok";
        this.data = "success";
    }

    public ApiResponseDTO(Object data) {
        this.code = 0;
        this.msg = "ok";
        this.data = data;
    }

    public ApiResponseDTO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ApiResponseDTO getFailResponse(String msg){
        return new ApiResponseDTO(1,msg,"failure");
    }

}