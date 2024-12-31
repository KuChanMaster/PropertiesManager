package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Visitor;
import com.example.mapper.VisitorMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VisitorService {

    @Resource
    private VisitorMapper visitorMapper;

    /**
     * 新增
     */
    public void add(Visitor visitor) {
        visitor.setTime(DateUtil.now());
        visitorMapper.insert(visitor);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        visitorMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            visitorMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Visitor visitor) {
        visitorMapper.updateById(visitor);
    }

    /**
     * 根据ID查询
     */
    public Visitor selectById(Integer id) {
        return visitorMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Visitor> selectAll(Visitor visitor) {
        return visitorMapper.selectAll(visitor);
    }

    /**
     * 分页查询
     */
    public PageInfo<Visitor> selectPage(Visitor visitor, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Visitor> list = visitorMapper.selectAll(visitor);
        return PageInfo.of(list);
    }

}