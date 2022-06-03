package com.szqz.config;

import com.szqz.control.LoginUser;
import com.szqz.util.JwtUtil;
import com.szqz.util.RedisCache;
import io.jsonwebtoken.Claims;
//import org.apache.shiro.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * token认证过滤器
 *
 * 2022/1/5-14:12
 * 作用：解析请求头中的token。并验证合法性
 * 继承 OncePerRequestFilter 保证请求经过过滤器一次
 *//*
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");

        // 没有token
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);//放行,因为后面的会抛出相应的异常
            return;
        }
        System.out.println(request.getRequestURL());
        // 非法token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("非法token!");
        }


        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);// 从redis中获取用户信息

        // redis中用户不存在
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("redis中用户不存在!");
        }

        // 将Authentication对象（用户信息、已认证状态、权限信息）存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}

*/