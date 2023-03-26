package com.example.springboot_boot_jiekou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot_boot_jiekou.bean.Course;
import com.example.springboot_boot_jiekou.mapper.CourseMapper;
import com.example.springboot_boot_jiekou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    /**
     * 查询所有
     */
    @Override
    public List<Course> SelectCourse() {
        return courseMapper.SelectCoutse();
    }

    /**
     * 添加
     */
    @Override
    public int InsertCourse(Course course) {
        return courseMapper.InsertCourse(course);
    }

    /**
     * 修改
     */
    @Override
    public int UpdateCourse(Course course) {
        return courseMapper.UpdateCourse(course);
    }

    /**
     * 删除
     */
    @Override
    public int DeleteCourse(int id) {
        return courseMapper.DeleteCourse(id);
    }
}
