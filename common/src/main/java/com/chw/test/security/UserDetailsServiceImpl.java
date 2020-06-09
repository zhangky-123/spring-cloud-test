package com.chw.test.security;


import com.alibaba.fastjson.JSON;
import com.chw.test.dto.MyUserDetails;
import com.chw.test.exception.TokenException;
import com.chw.test.utils.jwt.CheckResult;
import com.chw.test.utils.jwt.JwtUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username就是用户输入的
        if(StringUtils.isEmpty(username)){
            throw new UsernameNotFoundException("用户不存在");
        }
        MyUserDetails myUserDetails;
        if(username.length()<16){
            myUserDetails= new MyUserDetails(1L,"张三","123456");
        }else {
            CheckResult checkResult = JwtUtils.validateJWT(username);
            if(checkResult.getSuccess()){
                myUserDetails= JSON.parseObject(checkResult.getClaims().getSubject(),MyUserDetails.class);
            }else {
                throw new TokenException(checkResult.getErrMsg());
            }
        }
        if(myUserDetails==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        myUserDetails.setUsername(String.valueOf(myUserDetails.getId()));
        //这里是要查询数据库的，先写死简化处理
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("authority1,authority2");
        myUserDetails.setAuthorities(grantedAuthorities);
        return myUserDetails;
    }
}
