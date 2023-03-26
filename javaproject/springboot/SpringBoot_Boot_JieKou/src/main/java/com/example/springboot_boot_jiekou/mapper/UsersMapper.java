package com.example.springboot_boot_jiekou.mapper;

import com.example.springboot_boot_jiekou.bean.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * CREATE TABLE users(
 * id INTEGER PRIMARY KEY AUTO_INCREMENT,
 * username VARCHAR(20),
 * password VARCHAR(20)
 * )
 */
@Mapper
public interface UsersMapper {
    /**
     * 查找账号
     *
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username} and password=#{password}")
    Users getByUserName(String username, String password);
}
