package com.szqz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szqz.entity.Comment;
import com.szqz.vo.ResultVo;

public interface CommentService extends IService<Comment> {

    // 添加
    ResultVo addComment(Comment comment);

    // 删除
    ResultVo deleteComment(int id);

    // 获取用户评论
    ResultVo userComment(String phoneNumber);

    // 获取升序评论
    ResultVo upperComment(String phoneNumber);

    // 获取降序评论
    ResultVo downComment(String phoneNumber);
}
