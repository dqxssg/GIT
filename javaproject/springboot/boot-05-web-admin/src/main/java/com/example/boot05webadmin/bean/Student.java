package com.example.boot05webadmin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
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
