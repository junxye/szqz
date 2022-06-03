package com.szqz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szqz.entity.Item;
import com.szqz.vo.ResultVo;

public interface ItemService extends IService<Item> {

    // 添加
    ResultVo addItem(Item item);

    // 删除
    ResultVo deleteItem(int id);

    // 更改
    ResultVo changeItem(Item item);

    //

}
