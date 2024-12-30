package com.example.controller;

import com.example.common.Result;
import com.example.entity.Charges;
import com.example.service.ChargesService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/charges")
public class ChargesController {

    @Resource
    private ChargesService chargesService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Charges charges) {
        chargesService.add(charges);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        chargesService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        chargesService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Charges charges) {
        chargesService.updateById(charges);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Charges charges = chargesService.selectById(id);
        return Result.success(charges);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Charges charges ) {
        List<Charges> list = chargesService.selectAll(charges);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Charges charges,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Charges> page = chargesService.selectPage(charges, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 业主缴费
     */
    @PostMapping("/pay")
    public Result pay(@RequestBody Charges charges) {
        chargesService.pay(charges);
        return Result.success();
    }

}