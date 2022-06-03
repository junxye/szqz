package com.szqz.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szqz.entity.Item;
import com.szqz.mapper.ItemMapper;
import com.szqz.service.ItemService;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service("itemService")
@Transient
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ResultVo addItem(Item item) {
/*
        Example example = new Example(Item.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemName", item.getItemName());
        List<Item> items = itemMapper.selectByExample(example);

        if (items.size() != 0) {
            for (Item i: items) {
                if (i.getSeller().equals(item.getSeller()))
                    return new ResultVo(ResStatus.NO, "商品名已存在", null);
            }
        }*/
        item.setSeller((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        item.setItemCheck((int)0);
        item.setIsSell((int)1);
        item.setAddTime(new Date());
        item.setCheckOpinion(null);
        item.setSellTime(null);
        //System.out.println(item.toString());
//        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        int i = baseMapper.insert(item);
/*
        int i = itemMapper.addItem(item);*/
        if (i > 0)
            return new ResultVo(ResStatus.OK, "商品添加成功", baseMapper.selectByPrimaryKey(item.getAddTime()));
        else
            return new ResultVo(ResStatus.NO, "商品添加失败", null);

    }

    @Override
    public ResultVo deleteItem(int id) {
        if (baseMapper.deleteById(id) > 0)
            return new ResultVo(ResStatus.OK, "删除成功", null);
        else
            return new ResultVo(ResStatus.NO, "删除失败", null);
    }

    @Override
    public ResultVo changeItem(Item item) {
        item.setSeller((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        baseMapper.updateById(item);
       /* Example example = new Example(Item.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("item_name", item.getItemName());
        List<Item> items = baseMapper.selectByExample(example);*/
        return null;
    }


}
