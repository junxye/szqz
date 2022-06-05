package com.szqz.mapper;

import com.szqz.entity.Item;
import com.szqz.service.GeneralDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
@Repository
public interface ItemMapper extends GeneralDAO<Item>, BaseMapper<Item> {

    // 添加
    int addItem(@Param("item") Item item);

    // 查询商品审核状态
    int getCheck(int id);

}
