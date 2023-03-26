package com.example.springboot_boot_jiekou.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot_boot_jiekou.bean.Student;
import com.example.springboot_boot_jiekou.bean.Zong;
import com.example.springboot_boot_jiekou.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController1 {
    @Autowired
    StudentService studentService;

    @GetMapping("/sj")
    public Object successPage() {
        List<Student> students = studentService.getStudent();
        return JSONObject.toJSON(new Zong(200, "查询成功", students));
    }
}