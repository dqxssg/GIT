package org.example;

import org.example.bean.Pet;
import org.example.bean.User;
import org.example.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.regex.Matcher;

/**
 * 主程序类
 * SpringBootApplication这是一个SpringBoot应用
 *
 * @SpringBootConfiguration
 * @Configuration代表当前是一个配置类
 * @ComponentScen指定扫描那些包
 * @EnableAutoConfiguration
 * @AutoConfigurationPackage自动配置包，指定了默认包规则，利用@getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件， 调用List<String>configyrations=getCandidateConfigurations(annotationMetadate,attributes)获得到所需要导入到容器中的配置类
 * 利用工厂加载Map<String,List<String>>loadSpringFactorise(@Nullable ClassLoader classLoader)，得到所有的组件，
 * 从META-INF/spring.factories位置来加载一个文件，默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
 * <p>
 * 配置文件中debug=true开启自动配置报告，Negative（不生效），Positive（生效）
 * 自定义器xxxCustomizer
 * @SpringBootConfiguration
 * @Configuration代表当前是一个配置类
 * @ComponentScen指定扫描那些包
 * @EnableAutoConfiguration
 * @AutoConfigurationPackage自动配置包，指定了默认包规则，利用@getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件， 调用List<String>configyrations=getCandidateConfigurations(annotationMetadate,attributes)获得到所需要导入到容器中的配置类
 * 利用工厂加载Map<String,List<String>>loadSpringFactorise(@Nullable ClassLoader classLoader)，得到所有的组件，
 * 从META-INF/spring.factories位置来加载一个文件，默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
 * <p>
 * 配置文件中debug=true开启自动配置报告，Negative（不生效），Positive（生效）
 * 自定义器xxxCustomizer
 * <p>
 * Lombok
 * 简化JavaBean开发
 * <p>
 * Spring Initailizr
 * 自动搭建结构
 * 项目初始化向导
 * <p>
 * YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）的递归缩写。在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。
 * @SpringBootConfiguration
 * @Configuration代表当前是一个配置类
 * @ComponentScen指定扫描那些包
 * @EnableAutoConfiguration
 * @AutoConfigurationPackage自动配置包，指定了默认包规则，利用@getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件， 调用List<String>configyrations=getCandidateConfigurations(annotationMetadate,attributes)获得到所需要导入到容器中的配置类
 * 利用工厂加载Map<String,List<String>>loadSpringFactorise(@Nullable ClassLoader classLoader)，得到所有的组件，
 * 从META-INF/spring.factories位置来加载一个文件，默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
 * <p>
 * 配置文件中debug=true开启自动配置报告，Negative（不生效），Positive（生效）
 * 自定义器xxxCustomizer
 * <p>
 * Lombok
 * 简化JavaBean开发
 * <p>
 * Spring Initailizr
 * 自动搭建结构
 * 项目初始化向导
 * <p>
 * YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）的递归缩写。
 * 在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。
 * @SpringBootConfiguration
 * @Configuration代表当前是一个配置类
 * @ComponentScen指定扫描那些包
 * @EnableAutoConfiguration
 * @AutoConfigurationPackage自动配置包，指定了默认包规则，利用@getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件， 调用List<String>configyrations=getCandidateConfigurations(annotationMetadate,attributes)获得到所需要导入到容器中的配置类
 * 利用工厂加载Map<String,List<String>>loadSpringFactorise(@Nullable ClassLoader classLoader)，得到所有的组件，
 * 从META-INF/spring.factories位置来加载一个文件，默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
 * <p>
 * 配置文件中debug=true开启自动配置报告，Negative（不生效），Positive（生效）
 * 自定义器xxxCustomizer
 * <p>
 * Lombok
 * 简化JavaBean开发
 * <p>
 * Spring Initailizr
 * 自动搭建结构
 * 项目初始化向导
 * <p>
 * YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）的递归缩写。
 * 在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。
 * @SpringBootConfiguration
 * @Configuration代表当前是一个配置类
 * @ComponentScen指定扫描那些包
 * @EnableAutoConfiguration
 * @AutoConfigurationPackage自动配置包，指定了默认包规则，利用@getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件， 调用List<String>configyrations=getCandidateConfigurations(annotationMetadate,attributes)获得到所需要导入到容器中的配置类
 * 利用工厂加载Map<String,List<String>>loadSpringFactorise(@Nullable ClassLoader classLoader)，得到所有的组件，
 * 从META-INF/spring.factories位置来加载一个文件，默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
 * <p>
 * 配置文件中debug=true开启自动配置报告，Negative（不生效），Positive（生效）
 * 自定义器xxxCustomizer
 * <p>
 * Lombok
 * 简化JavaBean开发
 * <p>
 * Spring Initailizr
 * 自动搭建结构
 * 项目初始化向导
 * <p>
 * YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）的递归缩写。
 * 在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。
 * 非常适合用来做以数据为中心的配置文件
 * 大小写敏感
 * #表示注解
 * "与"表示字符串内容会被转义/不转义
 * 使用缩进表示层级关系
 * 缩进不允许使用tab，只允许空格
 * 缩进的空格数不重要，只要相同层级的元素左对齐即可
 * @SpringBootConfiguration
 * @Configuration代表当前是一个配置类
 * @ComponentScen指定扫描那些包
 * @EnableAutoConfiguration
 * @AutoConfigurationPackage自动配置包，指定了默认包规则，利用@getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件， 调用List<String>configyrations=getCandidateConfigurations(annotationMetadate,attributes)获得到所需要导入到容器中的配置类
 * 利用工厂加载Map<String,List<String>>loadSpringFactorise(@Nullable ClassLoader classLoader)，得到所有的组件，
 * 从META-INF/spring.factories位置来加载一个文件，默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
 * <p>
 * 配置文件中debug=true开启自动配置报告，Negative（不生效），Positive（生效）
 * 自定义器xxxCustomizer
 * <p>
 * Lombok
 * 简化JavaBean开发
 * <p>
 * Spring Initailizr
 * 自动搭建结构
 * 项目初始化向导
 * <p>
 * YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）的递归缩写。
 * 在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。
 * 非常适合用来做以数据为中心的配置文件
 * 大小写敏感
 * #表示注解
 * "与"表示字符串内容会被转义/不转义
 * 使用缩进表示层级关系
 * 缩进不允许使用tab，只允许空格
 * 缩进的空格数不重要，只要相同层级的元素左对齐即可
 * 对象键值对使用冒号结构表示 key: value，冒号后面要加一个空格。也可以使用 key:{key1: value1, key2: value2, ...}。
 */

/**
 * @SpringBootConfiguration
 * @Configuration代表当前是一个配置类
 * @ComponentScen指定扫描那些包
 * @EnableAutoConfiguration
 * @AutoConfigurationPackage自动配置包，指定了默认包规则，利用@getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件，
 * 调用List<String>configyrations=getCandidateConfigurations(annotationMetadate,attributes)获得到所需要导入到容器中的配置类
 * 利用工厂加载Map<String,List<String>>loadSpringFactorise(@Nullable ClassLoader classLoader)，得到所有的组件，
 * 从META-INF/spring.factories位置来加载一个文件，默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
 */

/**
 * 配置文件中debug=true开启自动配置报告，Negative（不生效），Positive（生效）
 * 自定义器xxxCustomizer
 */

/**
 *Lombok
 * 简化JavaBean开发
 */
/**
 *Spring Initailizr
 * 自动搭建结构
 * 项目初始化向导
 */
/**
 * YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）的递归缩写。
 * 在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。
 * 非常适合用来做以数据为中心的配置文件
 * 大小写敏感
 * #表示注解
 * "与"表示字符串内容会被转义/不转义
 * 使用缩进表示层级关系
 * 缩进不允许使用tab，只允许空格
 * 缩进的空格数不重要，只要相同层级的元素左对齐即可
 * 对象键值对使用冒号结构表示 key: value，冒号后面要加一个空格。也可以使用 key:{key1: value1, key2: value2, ...}。
 */

/**
 *dev-tools
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        //返回我们IOC容器
//        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
//        //查看容器里里面的组件
//        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }
//        //从容器中获取组件
//        Pet tom = run.getBean("tom", Pet.class);
//        Pet tom1 = run.getBean("tom", Pet.class);
//        System.out.println(tom == tom1);
//        MyConfig bean = run.getBean(MyConfig.class);
//        System.out.println(bean);
//        System.out.println("=========");
//        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法，SpringBoot总会检查这个组件是否在容器中有
//        //保持组件单实例
//        User user = bean.user01();
//        User user1 = bean.user01();
//        System.out.println(user == user1);
//        System.out.println("=========");
//        User user01 = run.getBean("user01", User.class);
//        Pet tom2 = run.getBean("tom", Pet.class);
//        System.out.println(user01.getPet() == tom2);
//        System.out.println("=========");
//        String[] beanNamesForType = run.getBeanNamesForType(User.class);
//        for (String s : beanNamesForType) {
//            System.out.println(s);
//        }
    }
}