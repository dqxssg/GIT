package com.example.boot05webadmin;

import jakarta.websocket.server.ServerEndpoint;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 错误400
 * 不带请求参数或者参数类型不对
 * 一般都是浏览器的参数没有传递正确
 * Bad Request
 *
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @MapperScan("com.example.boot05webadmin.mapper") 包扫描，扫描mapper文件
 * @ServletComponentScan 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 * @MapperScan("com.example.boot05webadmin.mapper") 包扫描，扫描mapper文件
 */

/**
 * @ServletComponentScan
 * 默认@SpringBootApplication同级别及以下的包
 * @ServletComponentScan(basePackages = "com.example.boot05webadmin")
 * 指定原生Servlet组件都放在哪里
 */

/**
 * @MapperScan("com.example.boot05webadmin.mapper")
 * 包扫描，扫描mapper文件
 */

/**
 * @SpringBootApplication(exclude = RedisAutoConfiguration.class)
 */
//@MapperScan("com.example.boot05webadmin.mapper")
@ServletComponentScan
@SpringBootApplication(exclude = RedisAutoConfiguration.class)
public class Boot05WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot05WebAdminApplication.class, args);
    }
}
