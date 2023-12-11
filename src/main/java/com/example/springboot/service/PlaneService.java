package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.plane;
import com.example.springboot.mapper.PlaneMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@MapperScan(basePackages = {"com.example.springboot.mapper"})
public class PlaneService extends ServiceImpl<PlaneMapper,plane>{
    @Autowired(required = false)
    private PlaneMapper planeMapper;
    public int UpdInfo(plane plane,String pid){
        return planeMapper.updinfo(plane,pid);
    }

    public int add(plane p) {
        return planeMapper.add(p);
    }

    public int del(int id) {
        return planeMapper.del(id);
    }

    public List<plane> CheckQuery(String content) {
        return planeMapper.CheckQuery(content);
    }
}