package com.szqz.mapper;

import com.szqz.entity.Item;
import com.szqz.service.GeneralDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Date;

@Mapper
@Repository
public interface UserBuyMapper extends GeneralDAO<Item>, BaseMapper<Item> {

    // 购买
    int buyItem(int id, String buyer, Date time);

    // 拍下
    int takeItem(int id, String buyer);

    // 删除拍下商品
    int deleteItem(int id);
}
