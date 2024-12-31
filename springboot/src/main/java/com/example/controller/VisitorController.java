package com.example.controller;

import com.example.common.Result;
import com.example.entity.Visitor;
import com.example.service.VisitorService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Resource
    private VisitorService visitorService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Visitor visitor) {
        visitorService.add(visitor);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        visitorService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        visitorService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Visitor visitor) {
        visitorService.updateById(visitor);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Visitor visitor = visitorService.selectById(id);
        return Result.success(visitor);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Visitor visitor ) {
        List<Visitor> list = visitorService.selectAll(visitor);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Visitor visitor,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Visitor> page = visitorService.selectPage(visitor, pageNum, pageSize);
        return Result.success(page);
    }

}