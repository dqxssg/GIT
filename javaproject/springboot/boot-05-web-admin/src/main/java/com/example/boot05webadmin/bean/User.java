package com.example.boot05webadmin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName("user") 指明数据库表名称
 */
@AllArgsConstructor//有参构造器
@NoArgsConstructor//无参构造器
@Data
@TableName("user")
public class User {

    /**
     * @TableField(exist = false)
     * 表示在数据库表里面不存在
     */
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String password;

    //数据库
    private int id;
    private String name;
    private int age;
    private String email;
}
