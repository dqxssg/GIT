package com.example.zhibiaojiankong.config;

import com.example.zhibiaojiankong.bean.Color;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyConfig {
    @Profile("prod")
    @Bean
    public Color red() {
        return new Color();
    }

    @Profile("test")
    @Bean
    public Color blue() {
        return new Color();
    }
}
