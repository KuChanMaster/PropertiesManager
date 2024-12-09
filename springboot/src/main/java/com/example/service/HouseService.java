package com.example.service;

import com.example.entity.House;
import com.example.mapper.HouseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HouseService {

    @Resource
    private HouseMapper houseMapper;

    /**
     * 新增
     */
    public void add(House house) {
        houseMapper.insert(house);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        houseMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            houseMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(House house) {
        houseMapper.updateById(house);
    }

    /**
     * 根据ID查询
     */
    public House selectById(Integer id) {
        return houseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<House> selectAll(House house) {
        return houseMapper.selectAll(house);
    }

    /**
     * 分页查询
     */
    public PageInfo<House> selectPage(House house, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<House> list = houseMapper.selectAll(house);
        return PageInfo.of(list);
    }

}