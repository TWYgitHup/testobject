package com.example.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;


import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        generate();
    }
    private static void generate(){
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC", "root", "root")
                .globalConfig(builder -> {
                    builder.author("绝交哥") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\vue+springboot练习\\springboot_vue\\src\\main\\java\\"); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent("com.example.springboot") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "E:\\vue+springboot练习\\springboot_vue\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableHyphenStyle().enableRestStyle();//开启@RestController
                    builder.addInclude("student"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
