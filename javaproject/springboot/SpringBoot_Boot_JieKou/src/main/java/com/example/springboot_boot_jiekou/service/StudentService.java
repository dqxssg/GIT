package com.example.springboot_boot_jiekou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot_boot_jiekou.bean.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentService extends IService<Student> {
    /**
     * 查询所有
     *
     * @return
     */
    List<Student> getStudent();

    /**
     * 查询所有department
     */
    List<String> getdepartment();

    /**
     * 新增
     */
    int addstudent(Student student);

    /**
     * 修改
     */
    int updateStudent(Student student);

    /**
     * 根据Id查询
     */
    Student getUpdateStudentId(int id);
}
