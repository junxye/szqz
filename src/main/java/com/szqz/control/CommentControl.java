package com.szqz.control;

import com.szqz.entity.Comment;
import com.szqz.service.CommentService;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentControl {

    @Resource
    CommentService commentService;

    @ApiOperation("添加评论")
    @PostMapping("/add")
    public ResultVo addComment(@Param("phoneNumber") String phoneNumber, @Param("message") String message,
                              @Param("grade") int grade){
        String buyer = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (buyer.equals(phoneNumber))
            return new ResultVo(ResStatus.NO, "不可给自己评论", null);
        Comment comment = new Comment(phoneNumber, buyer, message, grade);
        return commentService.addComment(comment);
    }

    @ApiOperation("删除评论")
    @PostMapping("/delete")
    public ResultVo deleteComment(@Param("id") int id){
        return commentService.deleteComment(id);
    }

    @ApiOperation("获取用户评论")
    @PostMapping("/user")
    public ResultVo userComment(@Param("phoneNumber") String phoneNumber){
        if (phoneNumber == null)
            return new ResultVo(ResStatus.NO, "获取用户账号为空", null);
        return commentService.userComment(phoneNumber);
    }

    @ApiOperation("升序评论")
    @PostMapping("/up")
    public ResultVo upperComment(@Param("phoneNumber") String phoneNumber){
        if (phoneNumber == null)
            return new ResultVo(ResStatus.NO, "获取用户账号为空", null);
        return commentService.upperComment(phoneNumber);
    }

    @ApiOperation("降序评论")
    @PostMapping("/down")
    public ResultVo downComment(@Param("phoneNumber") String phoneNumber){
        if (phoneNumber == null)
            return new ResultVo(ResStatus.NO, "获取用户账号为空", null);
        return commentService.downComment(phoneNumber);
    }
}
