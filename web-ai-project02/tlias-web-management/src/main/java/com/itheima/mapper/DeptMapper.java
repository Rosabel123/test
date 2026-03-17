package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //方式一 手动结果映射
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    //方式二 起别名
//    @Select("select id,id, name, create_time as createTime, update_time as updateTime from dept order by update_time desc;")
    @Select("select id,id, name, create_time, update_time from dept order by update_time desc;")
    public List<Dept> findAll();
    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);
    @Insert("INSERT INTO dept(name,create_time,update_time) VALUES(#{name},#{createTime},#{updateTime})")
    public void insert(Dept dept);
    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    public Dept getById(Integer id);
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    public void update(Dept dept);
}
