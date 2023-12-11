package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class plane {
    @TableField("id")
    private int id;
    @TableField("planeId")
    private String planeId;
    @TableField("planearrs")
    private String planearrs;
    @TableField("planedate")
    private String planedate;
}
