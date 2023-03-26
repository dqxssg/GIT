package com.example.boot05web01.bean;

import lombok.Data;

import java.util.Date;

/**
 * 姓名：<input name="username" value="zhangsan"><br>
 * 年龄：<input name="age" value="18"><br>
 * 生日：<input name="birth" value="2000-01-01"><br>
 * 宠物姓名：<input name="pet.name" value="xiaomao"><br>
 * 宠物姓名：<input name="pet.age" value="5"><br>
 */

@Data
public class Person {
    private String username;
    private Integer age;
    private Date birth;
    private Pet pet;
}
