package com.example.securitydemo.handler;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName MyUserDetail
 * @Description: TODO
 * @Author 有丶优秀的少年
 * @Date 2020/11/19
 * @Version V1.0
 **/
@Component
@Data
public class MyUserDetails implements UserDetails {

    private String password;
    private String username;
    boolean accountNonExpired;//是否没过期
    boolean accountNonLocked;//是否没被锁定
    boolean credentialsNonExpired;//是否没过期
    boolean enabled;   //账号是否可用
    Collection<? extends GrantedAuthority> authorities;//用户的权限集合

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
