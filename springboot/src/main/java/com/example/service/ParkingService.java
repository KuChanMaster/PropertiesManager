package com.example.service;

import com.example.entity.Parking;
import com.example.mapper.ParkingMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ParkingService {

    @Resource
    private ParkingMapper parkingMapper;

    /**
     * 新增
     */
    public void add(Parking parking) {
        parkingMapper.insert(parking);
    }
    /**
     * 删除
     */
    public void deleteById(Integer id) {
        parkingMapper.deleteById(id);
    }
    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            parkingMapper.deleteById(id);
        }
    }
    /**
     * 修改
     */
    public void updateById(Parking parking) {
        parkingMapper.updateById(parking);
    }
    /**
     * 根据ID查询
     */
    public Parking selectById(Integer id) {
        return parkingMapper.selectById(id);
    }
    /**
     * 查询所有
     */
    public List<Parking> selectAll(Parking parking) {
        return parkingMapper.selectAll(parking);
    }
    /**
     * 分页查询
     */
    public PageInfo<Parking> selectPage(Parking parking, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Parking> list = parkingMapper.selectAll(parking);
        return PageInfo.of(list);
    }

}