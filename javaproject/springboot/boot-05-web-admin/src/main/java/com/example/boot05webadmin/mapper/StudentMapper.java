package com.example.boot05webadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot05webadmin.bean.Student;
import com.example.boot05webadmin.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
