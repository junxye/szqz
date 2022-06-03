package com.szqz.control;

import com.szqz.service.ItemQueryService;
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
@RequestMapping("/item")
public class ItemQueryController {

    @Resource
    private ItemQueryService itemQueryService;

    @ApiOperation("在售查询")
    @PostMapping("/all")
    public ResultVo queryAll(@Param("state") int state){
        if (state == 0)
            return itemQueryService.selectByTime();
        else if (state == 1)
            return itemQueryService.selectByDescTime();
        else if (state == 2)
            return itemQueryService.selectByPrice();
        else if (state == 3)
            return itemQueryService.selectByDescPrice();
        else
            return new ResultVo(ResStatus.NO, "参数错误", null);
    }

    @ApiOperation("查询当前用户所有商品")
    @PostMapping("/seller")
    public ResultVo querySeller(){
        return itemQueryService.selectByUser((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @ApiOperation("关键词搜索")
    @PostMapping("/keyword")
    public ResultVo queryKeyword(@Param("keyword") String keyword){
        if (keyword == null)
            return new ResultVo(ResStatus.NO, "没有关键词", null);
        return itemQueryService.selectByKeyword(keyword);
    }
}
