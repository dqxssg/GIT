package org.example.Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UesrMapper;
import org.example.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

public class UserMapperTest {
    @Test
    public void testSelect() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper接口
        UesrMapper mapper = sqlSession.getMapper(UesrMapper.class);
        //执行方法
        String username = "张三";
        String password = "123";
        User user = mapper.select(username, password);
        System.out.println(user);
        //释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper接口
        UesrMapper mapper = sqlSession.getMapper(UesrMapper.class);
        //执行方法
        User user = mapper.selectById(1);
        System.out.println(user);
        //释放资源
        sqlSession.close();
    }


}
