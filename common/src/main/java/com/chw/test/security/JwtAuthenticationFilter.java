package com.chw.test.security;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 如果不想使用spring-session也可以使用jwt的方式
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private AuthenticationFailureHandler failureHandler;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,AuthenticationFailureHandler failureHandler) {
        super(authenticationManager);
        this.failureHandler=failureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(SecurityContextHolder.getContext().getAuthentication()==null){
            String token = request.getHeader("token");
            if(!StringUtils.isEmpty(token)){
                try {
                    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(token,"jwt" );
                    Authentication authResult = super.getAuthenticationManager().authenticate(authRequest);
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                    //加上这行代码 可以不创建session
                    request.setAttribute("__spring_security_session_mgmt_filter_applied", Boolean.TRUE);
                } catch (AuthenticationException e) {
                    failureHandler.onAuthenticationFailure(request,response,e);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

}
