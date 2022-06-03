package com.szqz.control;

import com.alibaba.fastjson.annotation.JSONField;
import com.szqz.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 重写UserDetails返回的用户信息
 * SpringSecurity返回的用户信息实体类
 */
@Data
@NoArgsConstructor
@ResponseBody
public class LoginUser implements UserDetails {

    private User user;//用户信息

    private List<String> permissions;//权限信息

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false) //fastjson注解,表示此属性不会被序列化，因为SimpleGrantedAuthority这个类型不能在redis中序列化
    private List<SimpleGrantedAuthority> authorities;

    /**
     * 获取权限信息
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 权限为空的时候才往遍历，不为空直接返回
        if (authorities != null) {
            return authorities;
        }
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        authorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }
        return authorities;
    }

    /**
     * 获取密码
     */
    @Override
    public String getPassword() {
        return user.getPassWord();
    }

    /**
     * 获取用户名
     */
    @Override
    public String getUsername() {
        return user.getPhoneNumber();
    }

    /**
     * 判断是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 是否没有超时
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

