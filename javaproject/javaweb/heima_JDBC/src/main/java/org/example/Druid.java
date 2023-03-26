package org.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

//Druid数据库连接池
public class Druid {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //获取数据库连接Connection
        Connection connection = dataSource.getConnection();
        System.out.println("123");
    }
}
