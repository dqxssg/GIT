package com.example.zhibiaojiankong.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @Profile(value = {"prod","default"})
 * default:默认环境
 * @Profile("配置文件") 指激活那个配置文件
 */
@Profile(value = {"prod", "default"})
@Component
@ConfigurationProperties("person")
@Data
public class Boss implements Person {
    private String name;
    private int age;
}
