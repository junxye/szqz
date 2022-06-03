package com.szqz.mapper;

import com.szqz.entity.Item;
import com.szqz.service.GeneralDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper extends GeneralDAO<Item>, BaseMapper<Item> {

    // 添加
    int addItem(@Param("item") Item item);

    //

}
