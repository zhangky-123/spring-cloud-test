package com.chw.test.config;

import com.chw.test.dto.ApiResponseDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponseDTO exceptionHandler(Exception e){
        e.printStackTrace();
        return ApiResponseDTO.getFailResponse(e.toString());
    }
}
