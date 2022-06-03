package com.szqz.service;

import com.szqz.entity.User;
import com.szqz.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface UserService {

    // 用户注册
    ResultVo userRegister(User user);

    // 删除
    ResultVo userDelete(String phoneNumber);

    // 信息更改
    ResultVo userModify(User user);
}
