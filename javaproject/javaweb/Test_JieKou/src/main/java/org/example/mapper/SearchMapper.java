package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Search;
import org.example.pojo.User;

import java.util.List;

public interface SearchMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  search")
    @ResultMap("ResultMap")
    List<Search> selectAll();

    /**
     * 插入数据
     *
     * @param search
     */
    @Insert("insert into search values (null,#{trackingnumber},#{time},#{site})")
    void add(Search search);

    /**
     * 修改
     *
     * @param search
     */
    @Update("update search set trackingnumber=#{trackingnumber},time=#{time},site=#{site}where id =#{id}")
    void update(Search search);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from search where id = #{id}")
    void deleteById(int id);
}