package com.example.boot05webadmin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * 默认的自动配置是判断容器中没有才会配@ConditionalOnMissingBean(DataSource.class)
 */

/**
 * @ConfigurationProperties("spring.datasource") 和配置文件中的属性datasource绑定
 */
@Deprecated
//@Configuration
public class MyDataSourceConfig {
    /**
     * stat监控
     * well防火墙
     */
//    @ConfigurationProperties("spring.datasource")
//    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setFilters("stat,well");
        return druidDataSource;
    }

    /**
     * 配置druid的监控页功能
     */
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        StatViewServlet statViewServlet = new StatViewServlet();
//        ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(statViewServlet, "/druid/*");
//        statViewServletServletRegistrationBean.addInitParameter("loginUsername","admin");
//        statViewServletServletRegistrationBean.addInitParameter("loginUsername","123456");
//        return statViewServletServletRegistrationBean;
//    }

    /**
     * WebStatFilter用于采集web-jdbc管理监控的数据
     */
//    @Bean
//    public FilterRegistrationBean webStatFilter() {
//        WebStatFilter webStatFilter = new WebStatFilter();
//        FilterRegistrationBean<WebStatFilter> webStatFilterFilterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(webStatFilter);
//        webStatFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));//拦截
//        webStatFilterFilterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");//拦截
//        return webStatFilterFilterRegistrationBean;
//    }
}
