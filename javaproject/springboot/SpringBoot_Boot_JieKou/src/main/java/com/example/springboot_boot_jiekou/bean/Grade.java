package com.example.springboot_boot_jiekou.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CREATE TABLE grade(
 * id INTEGER PRIMARY KEY AUTO_INCREMENT,
 * studentid VARCHAR(20),
 * courseid VARCHAR(20),
 * effort VARCHAR(20)
 * )
 */
@Data
@TableName("grade")
public class Grade {
    private int id;
    private String studentid;
    private String courseid;
    private String effort;
}
