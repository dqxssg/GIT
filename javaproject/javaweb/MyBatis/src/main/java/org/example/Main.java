package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UesrMapper;
import org.example.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//MyBatis
public class Main {
    public static void main(String[] args) throws IOException {
        //加载MyBatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象，执行sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper接口的代理对象
        UesrMapper mapper = sqlSession.getMapper(UesrMapper.class);
        List<User> users = mapper.selectAll();
        System.out.println(users);
        //释放资源
        sqlSession.close();
    }
}