package com.szqz.config;

import com.szqz.service.mpl.UserServiceImpl;
import com.szqz.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SelfAuthenticationProvider implements AuthenticationProvider{
    @Autowired
    UserServiceImpl userServiceImpl;

    //@Autowired
    //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String account= authentication.getName();     //获取用户名
        String password= (String) authentication.getCredentials();  //获取密码
        UserDetails userDetails= userServiceImpl.loadUserByUsername(account);
//        boolean checkPassword= passwordEncoder.matches(SecurityUtils.encryptPassword(password),userDetails.getPassword());
        boolean checkPassword = SecurityUtils.matchesPassword(password, userDetails.getPassword());

        System.out.println(account+" "+password);
        if(!checkPassword){
            throw new BadCredentialsException("密码不正确，请重新登录!");
        }
        // 将Authentication对象（用户信息、已认证状态、权限信息）存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account, password, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(userDetails.getAuthorities());
        //放行
//        filterChain.doFilter(request, response);
        return new UsernamePasswordAuthenticationToken(account,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
