package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Slf4j 会生成一个日志对象,可以直接使用log对象记录日志 不需要自己创建日志对象了(要记录日志就得有这个注解 但是这个注解是 lombok 提供的注解)
@Slf4j
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    // 部门列表
    //requestmethod 是 指定 请求方法只能是get
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    //GetMapping 是 Get 请求的一种快捷方式
    @GetMapping("/depts")
    public Result list() {
        log.info("查询部门列表");
        List<Dept> deptlist = deptService.findAll();
        return Result.success(deptlist);
    }

    //delect-controller代码实现

    //方法一: 通过原始的HttpServletRequest对象获取参数

//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        //通过request中的一个getParameter方法获取参数 里面填写的是参数名我们要获取的是id参数 返回的参数是一个字符串
//        String idStr = request.getParameter("id");
//        //将字符串转换为整数
//        Integer id = Integer.parseInt(idStr);
//        //调用service删除部门
//        deptService.deleteById(id);
//        return Result.success();
//    }

    //方法二:通过Spring提供的@RequestParam注解获取参数,将请求参数绑定给方法形参
    //注意事项:
    //1.@RequestParam注解默认是必填的,如果请求中没有该参数,则会报错
    //2.如果请求中没有该参数,可以设置required=false,则该参数为可选参数
    @DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer id) {
        log.info("根据id删除部门:{}",id);
        //调用service删除部门
        deptService.deleteById(id);
        return Result.success();
    }

    //方式三:如果参数请求名和形参变量名一样,直接定义方法形参即可接受(省略了@RequestParam注解)
//    @DeleteMapping("/depts/{id}")
//    public Result delete(Integer id) {
    //调用service删除部门
//        deptService.deleteById(id);
//        return Result.success();
//    }
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        deptService.insert(dept);
        return Result.success();
    }
    //路径参数:通过请求URL直接传递参数,使用{}来标识该路径参数,需要使用@pathvariable获取
    //我们想要获取传过来的路径参数就需要用该注解来获取
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        log.info("根据id查询部门:{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("更新部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}