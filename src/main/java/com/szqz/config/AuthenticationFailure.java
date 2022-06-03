package com.szqz.config;

import com.alibaba.fastjson.JSON;
import com.szqz.util.WebUtils;
import com.szqz.vo.ResultVo;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailure implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResultVo result = new ResultVo();
        if (exception instanceof UsernameNotFoundException)
            result.denyAccess(exception.getMessage());
        else if (exception instanceof BadCredentialsException)
            result.denyAccess("密码错误");
        else
            result.denyAccess(exception.getMessage());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
