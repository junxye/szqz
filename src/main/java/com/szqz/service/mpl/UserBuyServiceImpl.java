package com.szqz.service.mpl;

import com.szqz.mapper.ItemMapper;
import com.szqz.mapper.UserBuyMapper;
import com.szqz.service.UserBuyService;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("cartService")
@Transient
public class UserBuyServiceImpl implements UserBuyService {

    @Autowired
    private UserBuyMapper userBuyMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ResultVo buyItem(int id) {
        if (itemMapper.getCheck(id) != 1)
            return new ResultVo(ResStatus.NO, "商品未审核通过", id);
        if (userBuyMapper.getIsSell(id) != 1)
            return new ResultVo(ResStatus.NO, "商品已售出", id);
        String buyer = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date time = new Date();
        int i = userBuyMapper.buyItem(id, buyer, time);
        if (i > 0)
            return new ResultVo(ResStatus.OK, "购买成功", id);
        else
            return new ResultVo(ResStatus.NO, "购买失败", null);
    }

    @Override
    public ResultVo takeItem(int id) {
        if (itemMapper.getCheck(id) != 1)
            return new ResultVo(ResStatus.NO, "商品未审核通过", id);
        if (userBuyMapper.getIsSell(id) != 1)
            return new ResultVo(ResStatus.NO, "商品已售出", id);
        String buyer = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int i = userBuyMapper.takeItem(id, buyer);
        if (i > 0)
            return new ResultVo(ResStatus.OK, "商品添加购物成功", id);
        else
            return new ResultVo(ResStatus.NO, "商品添加购物失败", null);
    }

    @Override
    public ResultVo deleteItem(int id) {
        if (itemMapper.getCheck(id) != 1)
            return new ResultVo(ResStatus.NO, "商品未审核通过", id);
        if (userBuyMapper.getIsSell(id) == 0)
            return new ResultVo(ResStatus.NO, "商品已售出", id);
        if (userBuyMapper.getIsSell(id) == 1)
            return new ResultVo(ResStatus.NO, "商品未添加购物", id);
        int i = userBuyMapper.deleteItem(id);
        if (i > 0)
            return new ResultVo(ResStatus.OK, "取消购物成功", id);
        else
            return new ResultVo(ResStatus.NO, "取消购物失败", null);
    }
}
