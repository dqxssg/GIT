package com.example.springboot_boot_jiekou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_boot_jiekou.bean.Course;
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
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 查询所有
     */
    @Select("select  *from course")
    List<Course> SelectCoutse();

    /**
     * 修改
     */
    @Update("update course set subject=#{subject},courseid=#{courseid},credit=#{credit},semester=#{semester}, period=#{period}where id=#{id}")
    int UpdateCourse(Course course);

    /**
     * 新增
     */
    @Insert("insert  into course values (null, subject=#{subject},courseid=#{courseid},credit=#{credit},semester=#{semester}, period=#{period}where )")
    int InsertCourse(Course course);

    /**
     * 删除
     */
    @Delete("delete  from course where id=#{id}")
    int DeleteCourse(int id);
}
