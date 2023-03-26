package org.example.request;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/Main1", loadOnStartup = 1)
public class Main1 implements Servlet {

    /**
     * 初始化方法
     * 1、调用时机：默认情况下，Servlet被第一次访问时调用
     * 2、调用次数：一次
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务
     * 1、调用时机：每一次Servlet被访问时调用
     * 2、调用次数：多次
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法
     * 1、调用时机：内存释放或者服务器关闭的时候，Servlet对象会被销毁调用
     * 2、调用次数：一次
     * 2、
     */
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}