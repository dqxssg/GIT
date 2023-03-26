package com.example.springboot_boot_jiekou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_boot_jiekou.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 查询所有
     */
    @Select("select * from student")
    List<Student> getStudent();

    /**
     * 查询所有department
     */
    @Select("select department from student")
    List<String> getdepartment();

    /**
     * 根据Id查询
     */
    @Select("select * from student where id=#{id}")
    Student updateStudentId(int id);

    /**
     * 修改
     */
    @Update("update student set name=#{name},sex=#{sex},birth=#{birth},studentid=#{studentid},householdregister=#{householdregister},department=#{department},speciality=#{speciality},classes=#{classes},dormitory=#{dormitory} where id=#{id}")
    int updateStudent(Student student);

    /**
     * 添加
     *
     * @return
     */
    @Insert("insert into student values(null,#{name},#{sex},#{birth},#{studentid},#{householdregister},#{department},#{speciality},#{classes},#{dormitory})")
    int addStudent(Student student);
}
