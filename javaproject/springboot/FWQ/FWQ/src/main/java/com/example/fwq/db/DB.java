package com.example.fwq.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    private String className, url, username, password;
    private Connection con;
    private Statement stm;
    private ResultSet rs;

    public DB() {
        className = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf-8&useUnicode=true&characterEncoding=utf8&useSSL=false";
        username = "root";
        password = "1234";
    }

    public void loadDrive() {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("鏁版嵁搴撹繛鎺ュけ璐�" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void getCon() {
        loadDrive();
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("鏁版嵁搴撹繛鎺ュけ璐�" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void getStm() {
        getCon();
        try {
            stm = con.createStatement();
        } catch (Exception e) {
            System.out.println("鏁版嵁搴撹繛鎺ュけ璐�" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void getRs(String sql) {
        getStm();
        try {
            rs = stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("鏁版嵁搴撹繛鎺ュけ璐�" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closed() {
        try {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (con != null)
                con.close();
        } catch (Exception e) {
            System.out.println("鏁版嵁搴撹繛鎺ュけ璐�" + e.getMessage());
            e.printStackTrace();
        }
    }

    public int update(String sql) {
        int i = -1;
        if (sql != null && !sql.equals("")) {
            getStm();
            try {
                i = stm.executeUpdate(sql);
            } catch (Exception e) {
                System.out.println("鏁版嵁搴撴煡璇㈤敊璇�" + e.getMessage());
                e.printStackTrace();
            } finally {
                closed();
            }
        }
        return i;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getRs() {
        return rs;
    }
}