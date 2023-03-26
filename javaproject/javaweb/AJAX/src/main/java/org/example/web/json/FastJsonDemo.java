package org.example.web.json;

import com.alibaba.fastjson.JSON;

import java.nio.file.attribute.UserPrincipal;

public class FastJsonDemo {
    public static void main(String[] args) {
        //1.将JAVA对象转为JSON字符串
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("123456");
        String s1 = JSON.toJSONString(user);
        System.out.println(s1);//{"id":1,"password":"123456","username":"张三"}
        //2.将JSON字符串转为JAVA对象
        User user1 = JSON.parseObject("{\"id\":2,\"password\":\"456789\",\"username\":\"里斯\"}", User.class);
        System.out.println(user1);


    }
}
