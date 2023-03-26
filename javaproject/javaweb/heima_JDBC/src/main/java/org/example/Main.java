package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");//可写可不写，jar包提供
        //获取连接
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        //定义sql
        String sql = "update xx set id=2000 where name= '张珊'";
        //获取执行sql的对象
        Statement statement = connection.createStatement();
        //执行sql
        int count = statement.executeUpdate(sql);//受影响的行数
        //处理结果
        System.out.println(count);
        //释放资源
        statement.close();
        connection.close();
    }
}