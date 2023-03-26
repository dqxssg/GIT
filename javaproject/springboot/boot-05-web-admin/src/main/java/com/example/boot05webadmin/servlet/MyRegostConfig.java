package com.example.boot05webadmin.servlet;

import jakarta.servlet.ServletRegistration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * (proxyBeanMethods = true)
 * 保证依赖的组件始终是单实例的
 */
//@Configuration
public class MyRegostConfig {
    /**
     * servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        MyServlet myServlet = new MyServlet();

        return new ServletRegistrationBean(myServlet, "myservlet");
    }

    /**
     * filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        MyFilter myFilter = new MyFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my", "/123"));
        return filterRegistrationBean;
    }

    /**
     * listener
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        MySwervletContextListener mySwervletContextListener = new MySwervletContextListener();
        return new ServletListenerRegistrationBean(mySwervletContextListener);
    }
}
