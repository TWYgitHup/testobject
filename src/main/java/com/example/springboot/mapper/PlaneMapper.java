package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.plane;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlaneMapper extends BaseMapper<plane> {
    @Update("UPDATE `plane` SET `planeId`=#{plane.planeId},`planearrs`=#{plane.planearrs},`planedate`=#{plane.planedate} WHERE `id` = #{pid}")
    int updinfo(@Param("plane") plane plane,@Param("pid") String pid);
    @Insert("INSERT INTO `plane`(`planeId`,`planearrs`,`planedate`)VALUES(#{planeId},#{planearrs},#{planedate})")
    int add(plane plane);
    @Delete("DELETE FROM `plane` WHERE `id` = #{id}")
    int del(int id);
    List<plane> CheckQuery(@Param("content") String content);
}
