package com.example.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void add(User user) {
        // 从数据库中查出是否有相同用户名的业主
        User dbUser = userMapper.selectByUserName(user.getUsername());
        if (ObjectUtil.isNotEmpty(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        // 如果没有密码，给他个默认密码
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        // 如果没有角色， 给他个业主的角色
        if (ObjectUtil.isEmpty(user.getRole())) {
            user.setRole(RoleEnum.USER.name());
        }
        // 往user表里新增一条业主信息
        userMapper.insert(user);
    }

    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }

    public void updateById(User user) {
        userMapper.updateById(user);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.deleteById(id);
        }
    }

    public Account login(Account account) {
        Account dbUser = userMapper.selectByUserName(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        //生成token
        String tokeData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokeData, dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public User selectById(Integer id) {
        User dbuser = userMapper.selectById(id);
        //生成token
        String tokenData=dbuser.getId()+"-"+RoleEnum.USER.name();
        String token=TokenUtils.createToken(tokenData,dbuser.getPassword());
        return dbuser;
    }


    public void register(Account account) {
        User user = new User();
        BeanUtil.copyProperties(account, user);
        add(user);
    }

    public void updatePassword(Account account) {
        User dbUser = userMapper.selectByUserName(account.getUsername());
        if(ObjectUtil.isNull(dbUser)){
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if(!account.getPassword().equals(dbUser.getPassword())){
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        dbUser.setPassword(account.getNewPassword());
        userMapper.updateById(dbUser);
    }

    public void recharge(Double account) {
        Account currentUser =TokenUtils.getCurrentUser();
        User user=userMapper.selectById(currentUser.getId());
        user.setAccount(user.getAccount()+account);
        userMapper.updateById(user);
    }
}