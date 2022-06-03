package com.szqz.service.mpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szqz.entity.Item;
import com.szqz.mapper.ItemMapper;
import com.szqz.mapper.ItemQueryMapper;
import com.szqz.service.ItemQueryService;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemQueryService")
@Transient
public class ItemQueryServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemQueryService {

    @Autowired
    ItemQueryMapper itemQueryMapper;

    @Override
    public ResultVo selectByTime() {
//        List<Item> list = baseMapper.selectAll();
        List<Item> list = itemQueryMapper.selectByTime();
        if (list.size() == 0)
            return new ResultVo(ResStatus.NO, "null", null);
        else
            return new ResultVo(ResStatus.OK, "按时间查询结果", list);

    }

    @Override
    public ResultVo selectByDescTime() {
        List<Item> list = itemQueryMapper.selectByDescTime();
        if (list.size() == 0)
            return new ResultVo(ResStatus.NO, "null", null);
        else
            return new ResultVo(ResStatus.OK, "按时间查询结果", list);
    }

    @Override
    public ResultVo selectByPrice() {
        List<Item> list = itemQueryMapper.selectByPrice();
        if (list.size() == 0)
            return new ResultVo(ResStatus.NO, "null", null);
        else
            return new ResultVo(ResStatus.OK, "按时间查询结果", list);
    }

    @Override
    public ResultVo selectByDescPrice() {
        List<Item> list = itemQueryMapper.selectByDescPrice();
        if (list.size() == 0)
            return new ResultVo(ResStatus.NO, "null", null);
        else
            return new ResultVo(ResStatus.OK, "按时间查询结果", list);
    }

    @Override
    public ResultVo selectByUser(String phoneNumber) {
        List<Item> list = itemQueryMapper.selectByUser(phoneNumber);
        if (list.size() == 0)
            return new ResultVo(ResStatus.NO, "null", null);
        else
            return new ResultVo(ResStatus.OK, "按时间查询结果", list);
    }

    @Override
    public ResultVo selectByKeyword(String keyword) {
        List<Item> list = itemQueryMapper.selectByKeyword(keyword);
        if (list.size() == 0)
            return new ResultVo(ResStatus.NO, "null", null);
        else
            return new ResultVo(ResStatus.OK, "按时间查询结果", list);
    }
}
