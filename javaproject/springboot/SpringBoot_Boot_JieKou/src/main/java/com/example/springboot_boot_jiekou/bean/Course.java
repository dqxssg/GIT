package com.example.springboot_boot_jiekou.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CREATE TABLE course(
 * id INTEGER PRIMARY KEY AUTO_INCREMENT,
 * subject VARCHAR(20),
 * courseid VARCHAR(20),
 * credit VARCHAR(20),
 * semester VARCHAR(20),
 * period VARCHAR(20)
 * )
 */
@Data
@TableName("course")
public class Course {
    private int id;
    private String subject;
    private String courseid;
    private String credit;
    private String semester;
    private String period;
}
