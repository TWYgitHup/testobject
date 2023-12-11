package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.springboot.entity.plane;
import com.example.springboot.mapper.PlaneMapper;
import com.example.springboot.service.PlaneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/plane")
public class PlaneController {
    @Autowired(required = false)
    private PlaneService planeService;
    @Autowired(required = false)
    private PlaneMapper planeMapper;

    @PostMapping("/updinfo")
    public int UpdInfo(@RequestBody plane plane,@RequestParam String pid){
        return planeService.UpdInfo(plane,pid);
    }
    @PostMapping("/add")
    public int add(@RequestBody plane info){
        return planeService.add(info);
    }
    @PostMapping("/del")
    public int del(@RequestParam int id){
        return planeService.del(id);
    }

    @GetMapping("/CheckQuery")
    public PageInfo<plane> CheckQuery(@RequestParam int size, @RequestParam int pages,@RequestParam String content){
        PageHelper.startPage(pages, size);
        PageInfo<plane> pi = new PageInfo<>(planeService.CheckQuery(content));
        return pi;
    }
    @PostMapping("/CheckDel")
    public boolean CheckDel(@RequestBody List<Integer> id){
        System.out.println(id);
        return planeService.removeByIds(id);
    }
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<plane> planes = planeMapper.selectList(null);
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("id","主键");
        writer.addHeaderAlias("planeId","编号");
        writer.addHeaderAlias("planearrs","地址");
        writer.addHeaderAlias("planedate","日期");
        writer.write(planes,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
    }
    @PostMapping("/import")
    public boolean imports(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> read = reader.read(1);
        List<plane> planes = CollUtil.newArrayList();
        for (List<Object> objects : read) {
            plane plane = new plane();
            plane.setPlaneId(objects.get(1).toString());
            plane.setPlanearrs(objects.get(2).toString());
            plane.setPlanedate(objects.get(3).toString());
            planes.add(plane);
        }
        planeService.saveBatch(planes);
        return true;
    }
}
