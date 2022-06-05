package com.szqz.mapper;

import com.szqz.entity.User;
import com.szqz.service.GeneralDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
@Repository
public interface UserQueryMapper extends GeneralDAO<User>, BaseMapper<User> {

    // 获取所有用户
    List<User> queryAllUser();


}
