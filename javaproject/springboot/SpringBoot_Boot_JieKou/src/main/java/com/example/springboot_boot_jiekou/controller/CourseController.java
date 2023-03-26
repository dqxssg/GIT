package com.example.springboot_boot_jiekou.controller;

import com.example.springboot_boot_jiekou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;
    /**
     * 课程表界面
     */
}
