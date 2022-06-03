package com.szqz.mapper;

import com.szqz.entity.Comment;
import com.szqz.service.GeneralDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface CommentMapper extends GeneralDAO<Comment>, BaseMapper<Comment> {

    // 获取单条评论
    Comment queryComment(String buyer, Date time);

    // 获取用户评论
    List<Comment> queryUserComment(String phoneNumber);

    // 升序查看评论
    List<Comment> queryUpper(String phoneNumber);

    // 降序查看评论
    List<Comment> queryDown(String phoneNumber);
}
