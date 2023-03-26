package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.User;

import java.util.Collection;
import java.util.List;

public interface UesrMapper {
    List<User> selectAll();

    /**
     * 参数封装
     * 单个参数
     * 1、POJO类型：直接使用，属性名和参数占位符名称一致
     * 2、Map集合：直接使用，键名和参数占位符名称一致
     * 3、Collection：封装成Map集合
     * map.put("arg0",collection集合)
     * map.put("collection",collection集合)
     * 4、List：封装成Map集合
     * map.put("arg0",list集合)
     * map.put("collection",list)
     * map.put("list",list集合)
     * 5、Array：封装成Map集合
     * map.put("arg0",数组)
     * map.put("array",数组)
     * 6、其他类型：直接使用
     * 多个参数：封装为Map集合，可以使用@Param注解，替换Map集合中默认键名
     */

    User select(@Param("username") String username, @Param("password") String password);

    @Select("select *from tb_user where id=#{if}")
    User selectById(int id);
}
