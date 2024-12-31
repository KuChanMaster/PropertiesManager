package com.example.mapper;
import com.example.entity.Visitor;

import java.util.List;

public interface VisitorMapper {

    /**
     * 新增
     */
    int insert(Visitor visitor);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Visitor visitor);

    /**
     * 根据ID查询
     */
    Visitor selectById(Integer id);

    /**
     * 查询所有
     */
    List<Visitor> selectAll(Visitor visitor);

}