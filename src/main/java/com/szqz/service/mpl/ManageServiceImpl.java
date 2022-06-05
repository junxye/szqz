package com.szqz.service.mpl;

import com.szqz.entity.Item;
import com.szqz.entity.User;
import com.szqz.mapper.ItemMapper;
import com.szqz.mapper.ItemQueryMapper;
import com.szqz.mapper.UserQueryMapper;
import com.szqz.service.ManageService;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("manageService")
@Transient
public class ManageServiceImpl implements ManageService {

    @Autowired
    private UserQueryMapper userQueryMapper;

    @Autowired
    private ItemQueryMapper itemQueryMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ResultVo queryAllUser() {
        List<User> list = userQueryMapper.queryAllUser();
        if (list.size() > 0)
            return new ResultVo(ResStatus.OK, "获取成功", list);
        else
            return new ResultVo(ResStatus.NO, "获取信息为空", null);
    }

    @Override
    public ResultVo queryAllItems(int state) {
        if (state > 2 || state < 0)
            return new ResultVo(ResStatus.NO, "查询参数有误", state);
        List<Item> list = itemQueryMapper.selectAllItem(state);
        if (list.size() > 0)
            return new ResultVo(ResStatus.OK, "查询成功", list);
        else
            return new ResultVo(ResStatus.NO, "获取信息为空", null);
    }

    @Override
    public ResultVo manageItem(int id, int check, String opinion) {
        if (check > 2 || check < 0)
            return new ResultVo(ResStatus.NO, "修改参数有误", check);
        int i = itemQueryMapper.manageItem(id, check, opinion);
        if (i > 0)
            return new ResultVo(ResStatus.OK, "信息更新成功", itemMapper.selectById(id));
        else
            return new ResultVo(ResStatus.NO, "信息更改失败", null);
    }
}
