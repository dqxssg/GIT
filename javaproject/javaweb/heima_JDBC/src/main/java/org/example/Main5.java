package org.example;

import org.junit.Test;

import java.sql.*;

//PreparedStatement
//防止SQL注入
public class Main5 {
    @Test
    public void testPreparedStatement() throws Exception {
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String user = "456";
        String pwd = "1234";
        //定义sql
        String sql = "select *from tb_user where username= ?  and password= ?";
        //获取
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user);
        preparedStatement.setString(2,pwd);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("LOGIN");
        }else {
            System.out.println("Do not LOGIN");
        }
        connection.close();
        preparedStatement.close();
        resultSet.close();
    }

    //PreparedStatement原理
    //useServerPrepStmts=true开启预编译
    @Test
    public void testPreparedStatement1() throws Exception {
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String user = "456";
        String pwd = "1234";
        //定义sql
        String sql = "select *from tb_user where username= ?  and password= ?";
        //获取
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user);
        preparedStatement.setString(2,pwd);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("LOGIN");
        }else {
            System.out.println("Do not LOGIN");
        }
        connection.close();
        preparedStatement.close();
        resultSet.close();
    }
}
