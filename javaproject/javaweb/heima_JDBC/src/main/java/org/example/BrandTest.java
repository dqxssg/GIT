package org.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;
import pojo.Brand;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandTest {
    @Test
    public void testSelectAll() throws Exception {
        //加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //获取数据库连接Connection
        Connection connection = dataSource.getConnection();
        //定义sql
        String sql = "select*from tb_brand";
        //获取PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        //处理结果Lisv<Brand>，封装Brand对象，装在List<>集合
        Brand brand = null;
        List<Brand> brands = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String brandName = resultSet.getString("brand_name");
            String companyName = resultSet.getString("company_name");
            int ordered = resultSet.getInt("ordered");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);
            //装载集合
            brands.add(brand);
        }
        //释放志愿
        resultSet.close();
        connection.close();
        preparedStatement.close();
    }

    //添加
    @Test
    public void testAdd() throws Exception {
        //提交的数据
        String brandName = "香飘飘";
        String companyName = "香飘飘有限公司";
        int ordered = 1;
        String description = "全球销量领先";
        int status = 1;
        //加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //获取数据库连接Connection
        Connection connection = dataSource.getConnection();
        //定义sql
        String sql = "insert into tb_brand( brand_name, company_name, ordered, description, status )values (?,?,?,?,?);";
        //获取PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置SQL
        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);
        //执行SQL
        int count = preparedStatement.executeUpdate();
        //处理结果
        if (count > 0) {
            System.out.println("添加成功");
        }
        //释放志愿
        connection.close();
        preparedStatement.close();
    }

    //修改
    @Test
    public void testUpdata() throws Exception {
        //提交的数据
        int id = 4;
        String brandName = "香飘飘";
        String companyName = "香飘飘责任有限公司";
        int ordered = 1;
        String description = "全球销量领先";
        int status = 1;
        //加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //获取数据库连接Connection
        Connection connection = dataSource.getConnection();
        //定义sql
        String sql = "update tb_brand set brand_name=?, company_name=?, ordered=?, description=?, status=? where id=?";
        //获取PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置SQL
        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);
        preparedStatement.setInt(6, id);
        //执行SQL
        int count = preparedStatement.executeUpdate();
        //处理结果
        if (count > 0) {
            System.out.println("修改成功");
        }
        //释放志愿
        connection.close();
        preparedStatement.close();
    }

    //删除
    @Test
    public void testdeleteById() throws Exception {
        //提交的数据
        int id = 4;
        //加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //获取数据库连接Connection
        Connection connection = dataSource.getConnection();
        //定义sql
        String sql = "delete from tb_brand where id=?";
        //获取PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置SQL
        preparedStatement.setInt(1, id);
        //执行SQL
        int count = preparedStatement.executeUpdate();
        //处理结果
        if (count > 0) {
            System.out.println("删除成功");
        }
        //释放志愿
        connection.close();
        preparedStatement.close();
    }
}
