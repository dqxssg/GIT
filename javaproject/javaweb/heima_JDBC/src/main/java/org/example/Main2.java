package org.example;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//Statement
public class Main2 {
    //DML语句
    @Test
    public void testDML() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "update xx set id=5000 where name= '张珊'";
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(sql);//执行完DML语句，受影响的行数
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }
        System.out.println(count);
        statement.close();
        connection.close();
    }

    //DDL语句
    @Test
    public void testDDL() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "create database xxx";
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(sql);//执行完DDL语句，返回值可能是0
        System.out.println(count);
        statement.close();
        connection.close();
    }
}
