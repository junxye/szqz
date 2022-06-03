package com.szqz.control;

import com.szqz.entity.User;
import com.szqz.service.LoginService;
import com.szqz.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个坑：退出路径如果只有 "/logout" 会报403 ，原因不明
 * 解决方法：前面添加个前路径，或者换一个名就可以了
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody User user) {
        return loginService.login(user);
    }

    /**
     * 退出登录
     */
    @PostMapping("/user/logout")
    public ResultVo logout() {
        return loginService.logout();
    }
}

