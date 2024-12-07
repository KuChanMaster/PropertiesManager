package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {


        PageInfo<User> info=userService.selectPage(user,pageNum,pageSize);
        return Result.success(info);
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody User user) {
        System.out.println("con"+user.toString());
        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }
}
