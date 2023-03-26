//package org.example.request;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@WebServlet("/Main8")
//public class Main8 extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        /**
//         * 解决GET中文乱码
//         * 原因：Tomcat进行URL解码，默认字符集ISO-8859-1
//         */
//        String username = request.getParameter("username");
//        request.setCharacterEncoding("utf-8");
////        //1、先对乱码数据进行编码：转为字数组
////        byte[] bytes = username.getBytes(StandardCharsets.ISO_8859_1);
////        //2、字节数组解码
////        String s = new String(bytes, StandardCharsets.UTF_8);
//        //用该行代码替换上面两行代码
//        String s = new String(username.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        System.out.println(s);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        /**
//         * 解决POST中文乱码
//         * request.setCharacterEncoding("UTF-8");
//         * 设置字符输入流的编码
//         */
//        request.setCharacterEncoding("UTF-8");
//        String username = request.getParameter("username");
//        System.out.println(username);
//    }
//}
