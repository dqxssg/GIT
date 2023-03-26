package org.example.config;

import org.example.bean.Car;
import org.example.bean.Pet;
import org.example.bean.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.regex.Matcher;

/**
 * 配置类里面使用的@Bean标注在方法上面给容器注册组件，默认也是单实例的
 * 配置类本身也是组件
 * proxyBeanMethods:代理Bean的方法
 * Full(@Configuration(proxyBeanMethods = true)
 * Lite(@Configuration(proxyBeanMethods = false)
 * 组件依赖
 *
 * @Import({User.class})给容器中自动创建出这两个类型的组件,默认组件的名字就是全类名
 * @ImportResource("classpath:bean.xml")导入Spring配置文件
 */

/**
 * @EnableConfigurationProperties(Car.class)
 * 开启属配置绑定功能
 * 把这个组件自动注册到容器当中
 */
@EnableConfigurationProperties(Car.class)
@Import({User.class})
@Configuration(proxyBeanMethods = true)//告诉SpringBoot这是一个配置类相当于配置文件
public class MyConfig {
    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     *
     * @return
     */
    @Bean//给容器中添加组件，以方法名作为组件的id，返回类型就是组件类型，返回值（对象）就是组件在容器中的实例
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        //user组件依赖了Pet组件
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }

    @Bean("tom")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }

    /**
     * 替换组件
     * xxxAutoConfiguration组件xxxProperties里面那值application.properties
     */
//    @Bean
//    public CharacterEncodingFilter filter(){
//        return null;
//    }
}
