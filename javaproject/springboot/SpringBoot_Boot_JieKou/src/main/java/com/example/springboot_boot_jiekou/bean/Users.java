package com.example.springboot_boot_jiekou.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CREATE TABLE users(
 * id INTEGER PRIMARY KEY AUTO_INCREMENT,
 * username VARCHAR(20),
 * password VARCHAR(20)
 * )
 */
@Data
@TableName("users")
public class Users {
    private int id;
    private String username;
    private String password;
}
