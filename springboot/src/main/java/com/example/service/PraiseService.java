package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.entity.Activity;
import com.example.entity.Praise;
import com.example.mapper.ActivityMapper;
import com.example.mapper.PraiseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PraiseService {

    @Resource
    private PraiseMapper praiseMapper;
    @Resource
    private ActivityMapper activityMapper;

    /**
     * 新增
     */
    public void add(Praise praise) {
        praiseMapper.insert(praise);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        praiseMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            praiseMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Praise praise) {
        praiseMapper.updateById(praise);
    }

    /**
     * 根据ID查询
     */
    public Praise selectById(Integer id) {
        return praiseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Praise> selectAll(Praise praise) {
        return praiseMapper.selectAll(praise);
    }

    /**
     * 分页查询
     */
    public PageInfo<Praise> selectPage(Praise praise, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Praise> list = praiseMapper.selectAll(praise);
        return PageInfo.of(list);
    }

    public void doPraise(Praise praise) {
        List<Praise> list = praiseMapper.selectAll(praise);
        Activity activity = activityMapper.selectById(praise.getActivityId());
        if (CollectionUtil.isNotEmpty(list)) {
            // 取消点赞
            praiseMapper.deleteById(list.get(0).getId());
            activity.setCount(activity.getCount() - 1);
        } else {
            // 点赞
            praiseMapper.insert(praise);
            activity.setCount(activity.getCount() + 1);
        }
        // 更新帖子的点赞数
        activityMapper.updateById(activity);
    }
}