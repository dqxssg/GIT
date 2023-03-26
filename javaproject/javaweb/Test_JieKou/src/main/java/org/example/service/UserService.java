package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.CustomerMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Customer;
import org.example.pojo.User;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class UserService {
    //调用BrandMapper.selectAll()
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> selectAllUser() {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        List<User> users = mapper.selectAllUser();
        //释放资源
        sqlSession.close();
        return users;
    }


    /**
     * 添加
     *
     * @param user
     */
    public void UserAdd(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.userAdd(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改
     *
     * @param user
     */
    public void UserUpdate(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.userupdate(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     *
     * @param id
     */
    public void UserDeleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.userdeleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
