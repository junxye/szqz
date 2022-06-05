package com.szqz.control;

import com.szqz.service.ItemService;
import com.szqz.service.ManageService;
import com.szqz.service.UserService;
import com.szqz.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    ManageService manageService;

    @Resource
    UserService userService;

    @Resource
    ItemService itemService;

    @ApiOperation("查询所有用户")
    @PostMapping("/user")
    public ResultVo queryAllUser(){
        return manageService.queryAllUser();
    }

    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
    public ResultVo deleteUser(@Param("phoneNumber") String phoneNumber){
        return userService.userDelete(phoneNumber);
    }

    @ApiOperation("删除商品")
    @PostMapping("/deleteItem")
    public ResultVo deleteItem(@Param("id") int id){
        return itemService.deleteItem(id);
    }

    @ApiOperation("获取商品列表")
    @PostMapping("/items")
    public ResultVo queryAllItem(@Param("state") int state){
        return manageService.queryAllItems(state);
    }

    @ApiOperation("管理商品")
    @PostMapping("/manageItem")
    public ResultVo manageItem(@Param("id") int id, @Param("check") int check,
                               @Param("opinion") String opinion){
        return manageService.manageItem(id, check, opinion);
    }
}
