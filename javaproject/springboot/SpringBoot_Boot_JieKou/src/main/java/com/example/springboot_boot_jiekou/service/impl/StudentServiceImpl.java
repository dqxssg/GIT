package com.example.springboot_boot_jiekou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot_boot_jiekou.bean.Student;
import com.example.springboot_boot_jiekou.mapper.StudentMapper;
import com.example.springboot_boot_jiekou.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    /**
     * 查询所有
     */
    @Override
    public List<Student> getStudent() {
        return studentMapper.getStudent();
    }

    @Override
    public List<String> getdepartment() {
        return studentMapper.getdepartment();
    }

    /**
     * 新增
     */
    @Override
    public int addstudent(Student student) {
        return studentMapper.addStudent(student);
    }

    /**
     * 修改
     */
    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }


    /**
     * 根据Id查询
     */
    @Override
    public Student getUpdateStudentId(int id) {
        return studentMapper.updateStudentId(id);
    }
}
