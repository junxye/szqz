package com.szqz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szqz.entity.Item;
import com.szqz.vo.ResultVo;

public interface ItemQueryService extends IService<Item> {

    // 按时间顺序
    ResultVo selectByTime();

    // 按时间逆序
    ResultVo selectByDescTime();

    // 按价格升序
    ResultVo selectByPrice();

    // 按价格降序
    ResultVo selectByDescPrice();

    // 查询用户发布
    ResultVo selectByUser(String phoneNumber);

    // 关键词
    ResultVo selectByKeyword(String keyword);
}
