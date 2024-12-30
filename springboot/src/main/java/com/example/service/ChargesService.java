package com.example.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.example.entity.Charges;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.ChargesMapper;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChargesService {
    @Resource
    private ChargesMapper chargesMapper;
    @Autowired
    private UserMapper userMapper;


    /**
     * 新增
     */
    public void add(Charges charges) {


        chargesMapper.insert(charges);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        chargesMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            chargesMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Charges charges) {
        chargesMapper.updateById(charges);
    }

    /**
     * 根据ID查询
     */
    public Charges selectById(Integer id) {
        return chargesMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Charges> selectAll(Charges charges) {
        return chargesMapper.selectAll(charges);
    }

    /**
     * 分页查询
     */
    public PageInfo<Charges> selectPage(Charges charges, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Charges> list = chargesMapper.selectAll(charges);
        return PageInfo.of(list);
    }

    /*
     * 业主缴费
     */
    public void pay(Charges charges){
        //判断业主余额是否足够
        User user=userMapper.selectById(charges.getUserId());
        if(user.getAccount()<charges.getPrice()){
            throw new CustomException("-1","余额不足,请前往个人中心充值");
        }
        //更新用户余额信息
        user.setAccount(user.getAccount()-charges.getPrice());
        userMapper.updateById(user);
        //更新计费清单信息
        charges.setStatus("已缴费");
        charges.setTime(DateUtil.now());
        chargesMapper.updateById(charges);
    }

}

