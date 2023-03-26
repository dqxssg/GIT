package com.example.springboot_boot_jiekou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_boot_jiekou.bean.Grade;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * CREATE TABLE grade(
 * id INTEGER PRIMARY KEY AUTO_INCREMENT,
 * studentid VARCHAR(20),
 * courseid VARCHAR(20),
 * effort VARCHAR(20)
 * )
 */
@Mapper
public interface GradeMapper extends BaseMapper<Grade> {
    /**
     * 查询所有
     */
    @Select("select * from grade")
    List<Grade> SelectGrade();

    /**
     * 添加
     */
    @Insert("insert into grade values (null,studentid=#{studentid},courseid=#{courseid},effort=#{effort})")
    int InsertGrade(Grade grade);

    /**
     * 修改
     */
    @Update("update grade set studentid=#{studentid},courseid=#{courseid},effort=#{effort} where id=#{id}")
    int UpdateGrade(Grade grade);

    /**
     * 删除
     */
    @Delete("delete from grade where id=#{id}")
    int DeleteGrade(int id);
}
