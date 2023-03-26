package org.example.request;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebServlet("/Main3")
public class Main3 implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //根据请求方式不同，进行分别的处理
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //1、获取请求方式
        String method = request.getMethod();
        //2、判断
        if ("GET".equals(method)) {
            //GET处理方式的逻辑
        } else if ("POST".equals(method)) {
            //POST处理方式的逻辑
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}