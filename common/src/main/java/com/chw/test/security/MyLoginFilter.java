package com.chw.test.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 只有这个方法是重写的，其余都是从UsernamePasswordAuthenticationFilter中拷贝的
     * 目的是为了从body中读取登录数据
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {

            String username = "";
            String password = "";

            try {
                BufferedReader reader = request.getReader();
                String str;
                StringBuilder stringBuilder = new StringBuilder();
                while ((str=reader.readLine())!=null){
                    stringBuilder.append(str);
                }
                String fullStr = stringBuilder.toString();
                if(!StringUtils.isEmpty(fullStr)){
                    Map map = JSON.parseObject(fullStr, Map.class);
                    String str1 = (String) map.get(this.getUsernameParameter());
                    if(!StringUtils.isEmpty(str1)){
                        username=str1;
                    }
                    String str2 = (String) map.get(this.getPasswordParameter());
                    if(!StringUtils.isEmpty(str2)){
                        password=str2;
                    }
                }
                reader.close();
            } catch (IOException e) {

            }
            if(StringUtils.isEmpty(username)){
                username=request.getParameter(this.getUsernameParameter());
            }
            if(StringUtils.isEmpty(password)){
                password=request.getParameter(this.getPasswordParameter());
            }
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

}
