package com.example.boot05webadmin;

import com.alibaba.druid.support.http.StatViewServlet;
import com.example.boot05webadmin.bean.User;
import com.example.boot05webadmin.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
class Boot05WebAdminApplicationTests {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    @Resource
    UserMapper userMapper;
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;


    @Test
    void contextLoads() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        System.out.println("数据条数：" + aLong);
        System.out.println("数据源类型：" + dataSource.getClass());
    }

    @Test
    void testUserMapper() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

//    @Test
//    void testRedis() {
//        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
//        stringStringValueOperations.set("hello", "world");
//        String s = stringStringValueOperations.get("hello");
//        System.out.println(s);
//        System.out.println(redisConnectionFactory.getClass());
//    }
}
