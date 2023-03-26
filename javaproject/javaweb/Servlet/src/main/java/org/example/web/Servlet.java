package org.example.web;

import org.example.pojo.Brand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、准备数据
        ArrayList<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1, "三只松鼠1", "三只松鼠2", 100, "三只松鼠3", 1));
        brands.add(new Brand(2, "小米1", "小米2", 200, "小米3", 0));
        brands.add(new Brand(3, "苹果1", "苹果2", 300, "苹果3", 1));
        //2、存储到resquest域中
        req.setAttribute("brands", brands);
        req.setAttribute("status", 1);
        //2、转发到
        //el-demo.jsp
//        req.getRequestDispatcher("/el-demo.jsp").forward(req, resp);
        //jstl-if.jsp
//        req.getRequestDispatcher("/jstl-if.jsp").forward(req, resp);
        //jstl-foreach.jsp
        req.getRequestDispatcher("/jstl-foreach.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
