package com.chw.test.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义记住我服务实现 主要是为了从header中读取记住我属性
 */
public class MyRememberMeServices extends PersistentTokenBasedRememberMeServices {

    public MyRememberMeServices(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
    }

    /**
     * 只有这个方法是重写的
     * 其他内容都是从PersistentTokenBasedRememberMeServices里面拷贝的
     * 重写这个方法的目的 就是从header中读取remember-me
     */
    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
        String paramValue = request.getHeader(parameter);
        if (paramValue != null && (paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("on") || paramValue.equalsIgnoreCase("yes") || paramValue.equals("1"))) {
            return true;
        }
        return super.rememberMeRequested(request,parameter);
    }

}
