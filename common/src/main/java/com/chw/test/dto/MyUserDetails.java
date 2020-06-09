package com.chw.test.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 自定义的UserDetails
 * 如果使用了会话管理的功能，一定要重写equals和hashcode方法
 */
public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String loginName;
    private String nickName;
    private String phone;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails() {
    }

    public MyUserDetails(Long id, String nickName, String password) {
        this.id = id;
        this.nickName = nickName;
        this.username = String.valueOf(id);
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object rhs) {
        return rhs instanceof MyUserDetails && this.username.equals(((MyUserDetails) rhs).getUsername());
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}
