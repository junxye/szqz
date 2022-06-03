package com.szqz.service.mpl;

import com.szqz.entity.User;
import com.szqz.mapper.UserMapper;
import com.szqz.service.UserService;
import com.szqz.util.SecurityUtils;
import com.szqz.vo.ResStatus;
import com.szqz.vo.ResultVo;
import org.springframework.security.core.context.SecurityContextHolder;
import tk.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    // 加载用户
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        //mybatis-plus帮我们写好了sql语句，相当于 select * from user where account ='${account}'
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", phoneNumber);
        User user = userMapper.selectOne(wrapper);

        if (user == null)
            throw new UsernameNotFoundException("电话号码不存在");
        //获取用户权限，并把其添加到GrantedAuthority中
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(user.getRole());
        grantedAuthorities.add(grantedAuthority);
        //方法的返回值要求返回UserDetails这个数据类型，  UserDetails是接口，找它的实现类就好了
        return new org.springframework.security.core.userdetails.User(phoneNumber,user.getPassWord(),grantedAuthorities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVo userRegister(User user) {
        // 是否已注册
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phoneNumber", user.getPhoneNumber());
        List<User> users = userMapper.selectByExample(example);

        // 如没注册
        if (users.size() == 0){
            user.setPassWord(SecurityUtils.encryptPassword(user.getPassWord()));
            user.setAddTime(new Date());
            user.setAccount(user.getPhoneNumber());
            user.setSex(null);
            user.setIntroduce(null);
            user.setGrade((double) 5.0);
            user.setRole("ROLE_USER");
            int i = userMapper.insertUseGeneratedKeys(user);

            if (i > 0)
                return new ResultVo(ResStatus.OK, "注册成功", user);
            else
                return new ResultVo(ResStatus.NO, "注册失败", null);
        }
        else
            return new ResultVo(ResStatus.NO, "该账号已注册", user);
    }

    @Override
    public ResultVo userDelete(String phoneNumber) {
        return null;
    }

    @Override
    public ResultVo userModify(User user) {
        user.setPhoneNumber((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        int i = userMapper.updateById(user);
        if (i > 0)
            return new ResultVo(ResStatus.OK, "修改成功", user);
        else
            return new ResultVo(ResStatus.NO, "修改失败", null);
    }


}
