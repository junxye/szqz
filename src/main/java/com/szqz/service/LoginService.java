package com.szqz.service;

import com.szqz.entity.User;
import com.szqz.vo.ResultVo;

public interface LoginService {
    /**
     * 登录
     * @return
     */
    ResultVo login(User user);

    /**
     * 退出登录
     */
    ResultVo logout();
}

