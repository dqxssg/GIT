package org.example;

import org.apache.commons.io.input.ReaderInputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.pojo.User;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接收用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2、调用MyBatis完成查询
        //2、1获取对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、2获取对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2、3获取Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //2、4调用方法
        User user = mapper.select(username, password);
        //2、5释放资源
        sqlSession.close();
        //获取字符输出流，并设置content type
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        //3、判断user是否为null
        if (user != null) {
            //登录成功
            writer.write("登录成功");
        } else {
            //登录失败
            writer.write("登录失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
