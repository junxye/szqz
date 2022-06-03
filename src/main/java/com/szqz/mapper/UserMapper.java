package com.szqz.mapper;

import com.szqz.entity.User;
import com.szqz.service.GeneralDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Repository
@Mapper
public interface UserMapper extends GeneralDAO<User>, BaseMapper<User>{
}
