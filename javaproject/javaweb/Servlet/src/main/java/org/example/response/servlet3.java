package org.example.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字符数据：设置字符数据的响应体
 */
@WebServlet("/servlet3")
public class servlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置流支持中文不乱码
        response.setContentType("text/html;charset=utf-8");
        //1、获取字符输出流
        PrintWriter writer = response.getWriter();
        //2、content-type
        response.setHeader("content-type","text/html");
        writer.write("aaa");
        writer.write("<h1>aaa</h1>");
        /**
         * 细节：流不需要关闭
         */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
