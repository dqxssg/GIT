package com.example.zhibiaojiankong;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;
import java.util.Objects;

/**
 * @EnableAdminServer 开启监控功能
 */
@EnableAdminServer
@SpringBootApplication
public class ZhiBiaoJianKongApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ZhiBiaoJianKongApplication.class);
        ConfigurableEnvironment environment = run.getEnvironment();
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        Map<String, Object> systemProperties = environment.getSystemProperties();
        System.out.println(systemEnvironment);
        System.out.println(systemProperties);
    }
}
