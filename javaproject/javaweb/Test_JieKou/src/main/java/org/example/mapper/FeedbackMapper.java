package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Feedback;
import org.example.pojo.User;

import java.util.List;

public interface FeedbackMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from feedback")
    @ResultMap("ResultMap")
    List<Feedback> selectAll();

    /**
     * 插入数据
     *
     * @param feedback
     */
    @Insert("insert into feedback values (null,#{time},#{trackingnumber},#{valuation})")
    void Add(Feedback feedback);

    /**
     * 修改
     *
     * @param feedback
     */
    @Update("update feedback set Time=#{time},trackingnumber=#{trackingnumber},valuation=#{valuation}where id =#{id}")
    void update(Feedback feedback);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from feedback where id = #{id}")
    void deleteById(int id);
}
