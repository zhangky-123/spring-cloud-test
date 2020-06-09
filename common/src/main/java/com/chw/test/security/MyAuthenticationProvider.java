package com.chw.test.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    /**
     * 利用构造方法注入UserDetailsService
     */
    public MyAuthenticationProvider(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
    }

    /**
     * 重写密码验证逻辑
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // UsernamePasswordAuthenticationToken 的 principal 是UserDetails的 username
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException("密码错误");
        }

        // UsernamePasswordAuthenticationToken 的 credentials 是 UserDetails的 password
        String presentedPassword = authentication.getCredentials().toString();
        // 覆写密码验证逻辑，真实场景这里肯定是加密后，再判断，这里简化处理
        if (!userDetails.getPassword().equals(presentedPassword) && !"jwt".equals(presentedPassword)) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException("密码错误");
        }
    }
}
