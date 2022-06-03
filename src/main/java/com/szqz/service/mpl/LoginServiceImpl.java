package com.szqz.service.mpl;

import com.szqz.control.LoginUser;
import com.szqz.entity.User;
import com.szqz.service.LoginService;
import com.szqz.util.JwtUtil;
import com.szqz.util.RedisCache;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResultVo login(User user) {
        // 认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassWord());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 认证没通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误!");
        }
        // 认证通过 生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>(1);
        map.put("token", jwt);
        // 认证通过 存入 redis
        redisCache.setCacheObject("login:" + userId, loginUser);
        return new ResultVo(ResStatus.LOGIN_SUCCESS, "登录成功", map);
    }

    @Override
    public ResultVo logout() {
        System.out.println(SecurityContextHolder.getContext());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Long userId = loginUser.getUser().getId();
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 清空redis
        redisCache.deleteObject("login:" + userId);
        return new ResultVo(ResStatus.OK, "退出成功", null);
    }
}

