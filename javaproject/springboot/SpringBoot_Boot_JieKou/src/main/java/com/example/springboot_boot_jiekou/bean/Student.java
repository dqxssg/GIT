package com.example.springboot_boot_jiekou.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student {
    private int id;
    private String name;
    private String sex;
    private String birth;
    private String studentid;
    private String householdregister;
    private String department;
    private String speciality;
    private String classes;
    private String dormitory;
}
