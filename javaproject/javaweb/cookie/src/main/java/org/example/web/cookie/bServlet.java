package org.example.web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/cookiebServlet")
public class bServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Cookie
        //获取Cookie数组
        Cookie[] cookies = request.getCookies();
        //遍历数组
        for (Cookie cookie : cookies) {
            //获取数据
            String name = cookie.getName();
            if ("username".equals(name)) {
                String value = cookie.getValue();
                //URL解码
                value = URLDecoder.decode(value, "UTF-8");
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
