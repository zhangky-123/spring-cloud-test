package com.chw.test.security;

import com.alibaba.fastjson.JSONObject;
import com.chw.test.dto.ApiResponseDTO;
import com.chw.test.enums.ResponseCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 已登录用户没有权限，自定义返回提示
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSONObject.toJSONString(new ApiResponseDTO(ResponseCodeEnum.No_Authority.getCode(),ResponseCodeEnum.No_Authority.getMsg(),"failure")));
    }

}
