package com.szqz.service.mpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szqz.entity.Comment;
import com.szqz.mapper.CommentMapper;
import com.szqz.service.CommentService;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResultVo addComment(Comment comment) {
        comment.setAddTime(new Date());
        int i = baseMapper.insert(comment);
        if (i > 0)
            return new ResultVo(ResStatus.OK, "评论添加成功", commentMapper.queryComment(comment.getBuyer(), comment.getAddTime()));
        else
            return new ResultVo(ResStatus.NO, "评论失败", null);
    }

    @Override
    public ResultVo deleteComment(int id) {
        if (baseMapper.deleteById(id) > 0)
            return new ResultVo(ResStatus.OK, "删除成功", null);
        else
            return new ResultVo(ResStatus.NO, "删除失败", null);
    }

    @Override
    public ResultVo userComment(String phoneNumber) {
        List<Comment> list = commentMapper.queryUserComment(phoneNumber);
        if (list.size() > 0)
            return new ResultVo(ResStatus.OK, "获取成功", list);
        else
            return new ResultVo(ResStatus.NO, "null", null);
    }

    @Override
    public ResultVo upperComment(String phoneNumber) {
        List<Comment> list = commentMapper.queryUpper(phoneNumber);
        if (list.size() > 0)
            return new ResultVo(ResStatus.OK, "获取成功", list);
        else
            return new ResultVo(ResStatus.NO, "null", null);
    }

    @Override
    public ResultVo downComment(String phoneNumber) {
        List<Comment> list = commentMapper.queryDown(phoneNumber);
        if (list.size() > 0)
            return new ResultVo(ResStatus.OK, "获取成功", list);
        else
            return new ResultVo(ResStatus.NO, "null", null);
    }
}
