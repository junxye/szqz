package com.szqz.control;

import com.szqz.entity.Item;
import com.szqz.service.ItemQueryService;
import com.szqz.service.ItemService;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;

    @ApiOperation("添加商品")
    @PostMapping("/addItem")
    public ResultVo addItem(@Param("name") String name, @Param("price") double price,
                            @Param("introduce") String introduce, @Param("picture") String picture){
        if (name == null || price == 0.0)
            return new ResultVo(ResStatus.NO, "未输入商品名称和价格", null);
        if (introduce == null) introduce = name;
        if (picture == null) picture = "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";
        Item item = new Item(name, (double) price, introduce, picture);
        //return itemService.addItem(item);
        return new ResultVo(000, item.toString(), item);
    }

    @ApiOperation("修改商品信息")
    @PostMapping("/modifyItem")
    public ResultVo modifyItem(@Param("id") int id, @Param("name") String name, @Param("price") double price,
                               @Param("introduce") String introduce, @Param("picture") String picture){
        if (id == 0)
            return new ResultVo(ResStatus.NO, "无商品编号", null);
        Item item = new Item(name, price, introduce, picture);
        item.setId(id);
        item.setItemCheck(0);
        if (itemService.saveOrUpdate(item))
            return new ResultVo(ResStatus.OK, "信息修改成功", itemService.getById(id));
        else
            return new ResultVo(ResStatus.NO, "信息修改失败", null);
    }

    @ApiOperation("删除商品")
    @PostMapping("/deleteItem")
    public ResultVo deleteItem(@Param("id") int id){
        if (id == 0)
            return new ResultVo(ResStatus.NO, "无编号", null);
        return itemService.deleteItem(id);
    }

    @ApiOperation("商品详细信息")
    @PostMapping("/message")
    public ResultVo queryMessage(@Param("id") int id){
        if (id == 0)
            return new ResultVo(ResStatus.NO, "无编号", null);
        Item item = itemService.getById(id);
        if (item == null)
            return new ResultVo(ResStatus.NO, "获取失败", null);
        else
            return new ResultVo(ResStatus.OK, "获取成功", item);
    }
}
