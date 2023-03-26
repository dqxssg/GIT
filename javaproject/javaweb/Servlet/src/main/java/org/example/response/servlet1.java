package org.example.response;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//重定向
@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("servlet1...");
//        //1、设置响应状态码302
//        response.setStatus(302);
//        //2、设置响应头Location
//        response.setHeader("Location","/Servlet/servlet2");

        //动态获取虚拟目录
        String contextPath = request.getContextPath();
        //简化
        response.sendRedirect(contextPath+"/servlet2");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
