package com.szqz.control;

import com.szqz.service.UserBuyService;
import com.szqz.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class CartController {

    @Resource
    UserBuyService userBuyService;

    @ApiOperation("购买商品")
    @PostMapping("/buy")
    public ResultVo buyItem(@Param("id") int id){
        return userBuyService.buyItem(id);
    }

    @ApiOperation("添加购买商品")
    @PostMapping("/set")
    public ResultVo takeItem(@Param("id") int id){
        return userBuyService.takeItem(id);
    }

    @ApiOperation("购买商品")
    @PostMapping("/cart")
    public ResultVo payItem(@Param("id") int id){
        return userBuyService.buyItem(id);
    }

    @ApiOperation("删除预购商品")
    @PostMapping("/cartDelete")
    public ResultVo deleteItem(@Param("id") int id){
        return userBuyService.deleteItem(id);
    }
}
