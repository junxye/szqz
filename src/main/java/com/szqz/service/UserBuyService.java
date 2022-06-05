package com.szqz.service;

import com.szqz.vo.ResultVo;

public interface UserBuyService {

    // 购买商品
    ResultVo buyItem(int id);

    // 拍下商品不购买
    ResultVo takeItem(int id);

    // 删除拍下商品
    ResultVo deleteItem(int id);
}
