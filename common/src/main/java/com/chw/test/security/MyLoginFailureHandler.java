package com.chw.test.security;

import com.alibaba.fastjson.JSONObject;
import com.chw.test.dto.ApiResponseDTO;
import com.chw.test.enums.ResponseCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败后的响应
 */
public class MyLoginFailureHandler implements AuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().print(JSONObject.toJSONString(new ApiResponseDTO(ResponseCodeEnum.Login_Error.getCode(),e.getMessage(),"failure")));
    }
}
