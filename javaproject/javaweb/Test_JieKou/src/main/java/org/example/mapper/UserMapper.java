package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Customer;
import org.example.pojo.User;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  user")
    @ResultMap("ResultMap")
    List<User> selectAllUser();

    /**
     * 插入数据
     *
     * @param user
     */
    @Insert("insert into user values (null,#{username},#{password})")
    void userAdd(User user);

    /**
     * 修改
     *
     * @param user
     */
    @Update("update user set username=#{username},password=#{password}where id =#{id}")
    void userupdate(User user);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void userdeleteById(int id);
}
