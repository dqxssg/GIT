package org.example;

import org.junit.Test;
import pojo.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//ResultSet
public class Main3 {
    //DQL语句
    @Test
    public void testSQL() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "select*from account";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int anInt = resultSet.getInt(1);
            int id = resultSet.getInt("id");
            System.out.println(anInt);
            System.out.println(id + "-----");
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * ResultSet案例：查询Account账户表数据，封装Account对象中，并且储存到ArrayList集合中
     * 1、定义实体类Account
     * 2、查询数据，封装Account对象中
     * 3、将Account对象存入ArrayList集合中
     */
    @Test
    public void testResultSet() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://10.212.21.64:3306/xxjavaweb?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "select *from account";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Account>accounts=new ArrayList<>();
        while (resultSet.next()) {
            Account account=new Account();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double money = resultSet.getDouble("money");
            account.setId(id);
            account.setName(name);
            account.setMoney(money);
            accounts.add(account);
        }
        System.out.println(accounts.get(1).getId());
        resultSet.close();
        statement.close();
        connection.close();
    }
}