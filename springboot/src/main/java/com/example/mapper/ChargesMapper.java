package com.example.mapper;

import com.example.entity.Charges;

import java.util.List;

public interface ChargesMapper {

    /**
     * 新增
     */
    int insert(Charges charges);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Charges charges);

    /**
     * 根据ID查询
     */
    Charges selectById(Integer id);

    /**
     * 查询所有
     */
    List<Charges> selectAll(Charges charges);

}