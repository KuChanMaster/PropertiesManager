package com.example.controller;

import com.example.common.Result;
import com.example.entity.Parking;
import com.example.service.ParkingService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Resource
    private ParkingService parkingService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Parking parking) {
        parkingService.add(parking);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        parkingService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        parkingService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Parking parking) {
        parkingService.updateById(parking);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Parking parking = parkingService.selectById(id);
        return Result.success(parking);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Parking parking ) {
        List<Parking> list = parkingService.selectAll(parking);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Parking parking,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Parking> page = parkingService.selectPage(parking, pageNum, pageSize);
        return Result.success(page);
    }

}