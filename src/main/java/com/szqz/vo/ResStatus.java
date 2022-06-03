package com.szqz.vo;

public class ResStatus {

    public static final int OK = 100;
    public static final int NO = 101;
    public static final int NO_PERMISSION = 102;    // 没有权限

    public static final int LOGIN_SUCCESS = 105;   //认证成功
    public static final int LOGIN_FAIL_NOT = 405;   //用户未登录
    public static final int LOGIN_FAIL_OVERDUE = 406; //用户登录失效

}
