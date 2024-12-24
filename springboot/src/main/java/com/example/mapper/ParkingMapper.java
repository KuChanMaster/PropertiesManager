package com.example.mapper;

import com.example.entity.Parking;

import java.util.List;

public interface ParkingMapper {

    /**
     * 新增
     */
    int insert(Parking parking);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Parking parking);

    /**
     * 根据ID查询
     */
    Parking selectById(Integer id);

    /**
     * 查询所有
     */
    List<Parking> selectAll(Parking parking);

}