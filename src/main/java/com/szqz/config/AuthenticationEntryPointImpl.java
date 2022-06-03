package com.szqz.config;

import com.alibaba.fastjson.JSON;
import com.szqz.util.WebUtils;
import com.szqz.vo.ResultVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ResultVo result = new ResultVo();
        result.fail("认证失败，请重新登录！");
        String json = JSON.toJSONString(result);
        // 将字符串渲染到客户端
        WebUtils.renderString(response, json);
    }
}

