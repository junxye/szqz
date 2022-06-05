package com.szqz.control;

import com.szqz.entity.User;
import com.szqz.service.UserService;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户注册接口")/*
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "phoneNumber", value = "电话",required = true),
            @ApiImplicitParam(dataType = "string",name = "passWord", value = "密码",required = true)
    })*/
    @PostMapping("/register")
    public ResultVo register(@Param("phoneNumber") String phoneNumber,
                             @Param("passWord") String passWord, @Param("picture") String picture){
        User user = new User(phoneNumber, passWord);
        if (picture != null) user.setPicture(picture);
        else user.setPicture("iVBORw0KGgoAAAANSUhEUgAAALQAAAC0CAIAAACyr5FlAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAByZJREFUeNrsnb9PHEcUgMcrClzZ0EEDsjsojGS6WDJIpoxMiri0uYaUxI1b4C+IKeMGnBIXYLnEEiCRLiikOHdG0Jgu0OHOeXOLgcN33MnZmXkz8326Ap32jpvdb9+bmZ0fN8yLLwagFQWnAJADkAOQA5ADkAOQA5ADkAOQA5ADADkAOQA5ADkAOQA5ADkAOQA5ADkAvtKj/PeNDZrpUfPwjpm40/T+3iezd2S2P5r1D+bk9OJ9OfjxqBkbsB+8zNa+/cj2vlmvX7x5+6aZHmkcP2iG+y7ely+0X944WD51jhwzM25/jHy/fPacg2N72Nt6ix9jDx5s/ePt8XXVJ/+G2qkJM/fN/FTTNWuJXAy5JEs79krM/dB0zVoiF/L1rr0qcw/sv+iIWCVfLsjxV65xS1Z27fFypBzfzY9f+tO83GlSCjk6RIvffuzqSqSBmFF7ozGKFAoDxuZsRmaU2W3tqVl+Qp2jkxkKz5G3sgu1VSIHZsRwBrTIUdYzQPz49QFyNCNmdGxoZML8o87NnIzkkNslqxpox/qptOGR44y5Byhx9W7REDzCyyG1jSu9mWD9GEeORh8zfMvjEeQw9ukDtAyowWvo4eWgKtrWjwEqpKAV5ADFclweMAGXOfmcvRxb+2jQgnIAUe5yvN7FhBZoGN6hIq3IXQIK75mCu4ScoloOMovOu0WFHGQWnXdLwekgp2iXY+UvrFBXAyu4Xcgp2uUgsyi8SRTJQVeptla9IjlIK8LhMXJA+7SCHC1gJKm2k6BIDoaEGR1DRzXK8ew+blxdKQQ5zk4KaaVEw6QEXXIwr0lhBNUix/QIVpwhaUXJXB4tc2WZRd1ULUUObedCUbVDx90SXg49UVQV00yHNMyVVVxDLzgLOtHQ4VFwCggeSuUgbGhOuIHloHvj+qp62OdNPWHvDA8NtoNjO4Lmbd35YCIpS7mqdYVX9Nl4yDFQIeVw3b1xcmoW39uVxf1gV2FvrHW//KSrVdW7jKy1DNOK3GczLh8i7H0yk6/8mXGZ2mplt7vd12E0Pzmc1jbkJhYzAo47fP4umviqUQ6nZf7pj8CbVFQ4hy9gV3oYOZxGy5VdFQPZKxwNGqpNF0YOp6Vd2jGJESqzFImV1u6QpWCKQ9msjavNr0IOpzlFycyoyhtiQTJLADmcllPDpKCxQbvzQQKZJYAcTssZPKdIUNycrT4LBOnt8N1DGrZXx3zd/tMFt3pt0dw9ZJYv9zyT1rccwWcuycWrPOZ7i7ie5Sj8lxBiqZMWyZcwGaptHquTQ8rGFISI4m6RcNkSzCwJy8E8+v9fm/Y55LbwWTCmSscVPPzJQdioBJ+7nvmTgwoHkYPIkc6Z9CSHhr0O05HjblpyMCG2ygQ9kpYcbB4bYxj29OBNT4Xj4NhOZqnK+FDlkv/r4SFcT1ZmlHIsbFT2bXIHL/8cIGlK8PAgR+GnJKlycmqnQax4X9LfT5oukilJQGqrvpelnkhGjhx6OPzPh/BwVp3LMdyXRQ+H/7GrHpJ1kYDgefIwgchxjyexRI4MmyqXeeZ9wXIP+Zq0Us11CjI2dmwgZjlyWClQbt+1p2Eq3a6fwLntIU07p5SLE80/CtYcG+pDjqrT3OYv6aSziNPKvQED8dbnqHMQPALJwXBz5CBshMssd5EDQoAcceP0CYtLOfq5dkSONgwROSJvzZJWIFBTFqJuzTqUg2E+yAGkFSByUBuNT45+5ADSCiAHIAcgByBHBHQcXsTqU/nK0XHNQhY1zFeOiWtX25G44nSnY+TQztrT1slF3tyc5YrnLYfUKv6esxvKnysifyxMOdldK216Ui2YpA8yCJEDYpNDyf6uOXBySuSANrhbcQo5IIQcZBZPaeUzkQNSSisHx1w40kobDpHDPU5zt0M5/K/bSjuWCilc8LYepxwitYdtHzJn/UOccpgQy8VnxcputGmlzCwED3eBeXHD7b9w3s/x/J1bu7Nl8b3zzgLnckgBam+4lFVXNermpfuUXfgpSVV77kHZR+DnfvPUfb6wEWAjtFTNmHzlKVP7e7ZSW/URCTEjSjnKyqkoQv30u5t+Ps0w/p/KSnKREtKz/h1tk8nffd9XN8yLL0FKazejmGKlhq5up8WNMI+4g8lRMj16tuk3q6R/m0Qkvi7thBz5EFiOc27ftJtSTdy1u3CIKxlOMBEJxIZ/jszWRy3PLLXIcQVJNxJL5CVxRaRJ0hWxQV7bjQghL4Vjo5TK0S6uDPU1vIlQF6lL7h1ZCQ4bEUL+1t9qi0OOdrqIKMP9Z8aULz0hQV7iwcG/9o9Ih7bEKsc1+ahUpNyI5FbvpRmzFcWbMgaUbH+96lJREKKIB/nK0T0ize3e7mz4nGnHTI/JFTriOsK8FUAOQA5ADkAOQA5ADkAOQA5ADkAOAOQA5ADkAOQA5ADkAOQA5ADkAOQAQA7ozH8CDACFmvHncc/C4QAAAABJRU5ErkJggg==");
        return userService.userRegister(user);
    }

    @ApiOperation("修改信息")
    @PostMapping("/user/modify")
    public ResultVo modify(@Param("name") String name, @Param("password") String password,
                           @Param("age") int age, @Param("sex") String sex, @Param("picture") String picture,
                           @Param("introduce") String introduce){
        User user = new User(name, password, age, sex, introduce, picture);
        return userService.userModify(user);
    }

    @ApiOperation("删除当前用户")
    @PostMapping("/user/delete")
    public ResultVo delete(){
        String phoneNumber = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.userDelete(phoneNumber);
    }

    @ApiOperation("查询用户商品")
    @PostMapping("/user/items")
    public ResultVo queryAll(@Param("state") int state){
        if (state == 0)
            return null;
        else if (state == 1)
            return null;
        else if (state == 2)
            return null;
        else
            return new ResultVo(ResStatus.NO, "参数错误", null);
    }
}
