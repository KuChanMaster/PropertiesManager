package com.example.mapper;

import com.example.entity.House;

import java.util.List;

public interface HouseMapper {

    /**
     * 新增
     */
    int insert(House house);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(House house);

    /**
     * 根据ID查询
     */
    House selectById(Integer id);

    /**
     * 查询所有
     */
    List<House> selectAll(House house);

}