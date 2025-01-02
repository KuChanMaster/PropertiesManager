package com.example.mapper;

import com.example.entity.Fixed;

import java.util.List;

public interface FixedMapper {

    /**
     * 新增
     */
    int insert(Fixed fixed);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Fixed fixed);

    /**
     * 根据ID查询
     */
    Fixed selectById(Integer id);

    /**
     * 查询所有
     */
    List<Fixed> selectAll(Fixed fixed);

}