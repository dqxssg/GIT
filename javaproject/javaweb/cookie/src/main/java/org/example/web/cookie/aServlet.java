package org.example.web.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/cookieaServlet")
public class aServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //URL编码
        String value = "张三";
        value = URLEncoder.encode(value, "UTF-8");
        //发送Cookie
        //创建Cookie对象
        Cookie cookie = new Cookie("username", value);
        //设置存活时间
        cookie.setMaxAge(7 * 24 * 60 * 60);
        //发送Cookie，response
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
