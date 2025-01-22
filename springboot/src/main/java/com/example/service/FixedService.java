package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Fixed;
import com.example.exception.CustomException;
import com.example.mapper.FixedMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FixedService {
    @Resource
    private FixedMapper fixedMapper;
    public void add(Fixed fixed) {
        if (ObjectUtil.isEmpty(fixed.getContent())) {
            throw new CustomException("-1", "物业报修内容不能为空");
        }
        Account currentUser = TokenUtils.getCurrentUser();
        fixed.setName(currentUser.getName());
        fixed.setTime(DateUtil.now());
        fixed.setStatus("待处理");
        fixedMapper.insert(fixed);}
    
    public void deleteById(Integer id) {
        fixedMapper.deleteById(id);}
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            fixedMapper.deleteById(id);
        }
    }
    public void updateById(Fixed fixed) {
        fixedMapper.updateById(fixed);
    }

    public Fixed selectById(Integer id) {
        return fixedMapper.selectById(id);
    }

    public List<Fixed> selectAll(Fixed fixed) {
        return fixedMapper.selectAll(fixed);
    }

    /**
     * 分页查询
     */
    public PageInfo<Fixed> selectPage(Fixed fixed, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Fixed> list = fixedMapper.selectAll(fixed);
        return PageInfo.of(list);
    }

}