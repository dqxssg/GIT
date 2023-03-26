package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.util.SqlSessionFactoryUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/reginsterSetvlet")
public class reginsterSetvlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //封装用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //获取SqlSessionFactory对象
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用的方法
        User user1 = mapper.selectByUsername(username);
        //判断用户对象是否为null
        if (user1 == null) {
            mapper.add(user);
            //提交事务
            sqlSession.commit();
            //释放资源
            sqlSession.close();
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("用户名已存在");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
