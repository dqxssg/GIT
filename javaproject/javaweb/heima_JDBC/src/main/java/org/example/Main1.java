package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Connerction
public class Main1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "update xx set id=5000 where name= '张珊'";
        String sql1 = "update xx set id=5000 where name= '李四'";
        Statement statement = connection.createStatement();
        try {
            //开启事务
            connection.setAutoCommit(false);
            int count = statement.executeUpdate(sql);
            int i = 3 / 0;
            System.out.println(count);
            int count1 = statement.executeUpdate(sql1);
            System.out.println(count1);
            //提交事务
            connection.commit();
        } catch (Exception e) {
            //回滚事务
            connection.rollback();
            throw new RuntimeException(e);
        }
        statement.close();
        connection.close();
    }
}
