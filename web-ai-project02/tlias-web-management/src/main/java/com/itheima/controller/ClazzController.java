package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @GetMapping("/clazzs")
    public Result findAll() {
        return Result.success(clazzService.findAll());
    }
    @PostMapping("/clazzs")
    public Result add(@RequestBody Clazz clazz) {
        clazzService.add(clazz);
        return Result.success();
    }
}
