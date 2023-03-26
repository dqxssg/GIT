package com.example.boot05webadmin.config;

import com.example.boot05webadmin.interceptor.LoginInterceptor;
import com.example.boot05webadmin.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 编写一个拦截器实现HandlerInterceptor接口
 * 拦截器注册到容器中（实现WebMvcConfigurer和addInterceptors）
 * 指定拦截器规则（如果拦截所有，静态资源也会被拦截）
 */

/**
 * @EnableWebMvc 全面接管SpringMvc
 * 静态资源、试图解析器、欢迎页等全部失效
 * 所有规则全部自己重新配置，实现定制和扩展功能
 */
//@EnableWebMvc
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    /**
     * Filter、Interceptor
     * Filter是servlet定义的原生组件
     * 好处：脱离Spring应用也能使用
     * Interceptor是Spring定义的接口
     * 好处：可以使用Spring的自动装配等功能
     */
//    @Autowired
//    RedisUrlCountInterceptor redisUrlCountInterceptor;

    /**
     * 定义静态资源行为
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 访问/static/**所有请求都去classpath:/static/下面进行匹配
         */
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//添加拦截。/**表示全部拦截，包括静态资源
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**", "/save");//放行
//        registry.addInterceptor(redisUrlCountInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**", "/save");
    }

    @Bean
    public WebMvcRegistrations webMvcRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return WebMvcRegistrations.super.getRequestMappingHandlerMapping();
            }
        };
    }
}
