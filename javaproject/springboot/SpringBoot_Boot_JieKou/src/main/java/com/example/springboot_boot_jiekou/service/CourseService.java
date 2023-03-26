package com.example.springboot_boot_jiekou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot_boot_jiekou.bean.Course;

import java.util.List;

public interface CourseService extends IService<Course> {
    /**
     * 查询所有
     */
    List<Course> SelectCourse();

    /**
     * 新增
     */
    int InsertCourse(Course course);

    /**
     * 修改
     */
    int UpdateCourse(Course course);

    /**
     * 删除
     */
    int DeleteCourse(int id);
}
