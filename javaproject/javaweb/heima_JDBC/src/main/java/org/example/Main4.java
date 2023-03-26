package org.example;

import org.junit.Test;
import pojo.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//PreparedStatement
//用户登录
public class Main4 {
    @Test
    public void testLogin() throws Exception {
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String user = "王五";
        String pwd = "1234";
        String sql = "select *from tb_user where username= '" + user + "'  and password= '" + pwd + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println("LOGIN");
        }
        connection.close();
        statement.close();
        resultSet.close();
    }

    //演示注入SQL语句
    @Test
    public void testLogin_Inject() throws Exception {
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String user = "asdfgh1234";
        String pwd = "'or'1'='1";
        String sql = "select *from tb_user where username= '" + user + "'  and password= '" + pwd + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println("LOGIN");
        }
        connection.close();
        statement.close();
        resultSet.close();
    }
}
