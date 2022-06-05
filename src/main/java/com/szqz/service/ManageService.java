package com.szqz.service;

import com.szqz.vo.ResultVo;

public interface ManageService {

    // 查询用户
    public ResultVo queryAllUser();

    // 查询所有商品
    public ResultVo queryAllItems(int state);

    // 管理商品
    public ResultVo manageItem(int id, int check, String opinion);

    //
}
