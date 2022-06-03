package com.szqz.mapper;

import com.szqz.entity.Item;
import com.szqz.service.GeneralDAO;
import com.szqz.vo.ResultVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

@Mapper
@Repository
public interface ItemQueryMapper extends GeneralDAO<Item>, BaseMapper<Item> {

    List<Item> selectByTime();

    List<Item> selectByDescTime();

    List<Item> selectByPrice();

    List<Item> selectByDescPrice();

    // 查询用户发布商品
    List<Item> selectByUser(String phoneNumber);

    // 关键词搜索
    List<Item> selectByKeyword(@Param("keyword") String keyword);
}
