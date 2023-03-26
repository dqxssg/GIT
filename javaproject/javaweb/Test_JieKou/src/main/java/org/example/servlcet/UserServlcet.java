package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.User;
import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class UserServlcet extends BaseServlet {
    protected UserService userService = new UserService();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.selectAllUser();
        String jsonString = JSON.toJSONString(users);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        User user = JSON.parseObject(s, User.class);
        userService.UserAdd(user);
        resp.getWriter().write("200");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        User user = JSON.parseObject(s, User.class);
        userService.UserDeleteById(user.getId());
        resp.getWriter().write("200");
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        User user = JSON.parseObject(s, User.class);
        userService.UserUpdate(user);
        resp.getWriter().write("200");
    }
}
