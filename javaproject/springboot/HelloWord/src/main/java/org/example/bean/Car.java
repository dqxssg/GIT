package org.example.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 */

/**
 * @Data生成get和set方法
 * @ToString生成toString方法
 * @AllArgsConstructor生成有参构造器
 * @NoArgsConstructor生成无参构造器
 * @EqualsAndHashCode
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    String brand;
    int price;
}
