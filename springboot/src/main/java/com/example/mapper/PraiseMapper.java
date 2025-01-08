package com.example.mapper;

import com.example.entity.Praise;

import java.util.List;

public interface PraiseMapper {

    /**
     * 新增
     */
    int insert(Praise praise);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Praise praise);

    /**
     * 根据ID查询
     */
    Praise selectById(Integer id);

    /**
     * 查询所有
     */
    List<Praise> selectAll(Praise praise);

}