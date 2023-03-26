package com.example.springboot_boot_jiekou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(exclude = RedisAutoConfiguration.class)
public class SpringBootBootJieKouApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBootJieKouApplication.class, args);
    }

}
