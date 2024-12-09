package com.example.controller;

import com.example.common.Result;
import com.example.entity.House;
import com.example.service.HouseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Resource
    private HouseService houseService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody House house) {
        houseService.add(house);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        houseService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        houseService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody House house) {
        houseService.updateById(house);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        House house = houseService.selectById(id);
        return Result.success(house);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(House house ) {
        List<House> list = houseService.selectAll(house);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(House house,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<House> page = houseService.selectPage(house, pageNum, pageSize);
        return Result.success(page);
    }

}