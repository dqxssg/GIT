package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Route;
import org.example.pojo.User;

import java.util.List;

public interface RouteMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  route")
    @ResultMap("ResultMap")
    List<Route> selectAll();

    /**
     * 插入数据
     *
     * @param route
     */
    @Insert("insert into user values (null,#{shippingsite},#{receivingstation})")
    void add(Route route);

    /**
     * 修改
     *
     * @param route
     */
    @Update("update user set shippingsite=#{shippingsite},receivingstation=#{receivingstation}where id =#{id}")
    void update(Route route);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from route where id = #{id}")
    void deleteById(int id);
}