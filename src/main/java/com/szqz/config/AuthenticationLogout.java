package com.szqz.config;

import com.alibaba.fastjson.JSON;
import com.szqz.util.WebUtils;
import com.szqz.vo.ResultVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationLogout implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResultVo result = new ResultVo();
        result.success("注销成功");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
