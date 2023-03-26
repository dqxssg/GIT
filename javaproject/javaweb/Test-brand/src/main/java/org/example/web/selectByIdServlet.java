package org.example.web;

import org.example.pojo.Brand;
import org.example.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/selectByIdServlet")
public class selectByIdServlet extends HttpServlet {
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收id
        String id = request.getParameter("id");
        //调用brandService
        Brand brand = brandService.selectById(Integer.parseInt(id));
        //储存到request中
        request.setAttribute("brand", brand);
        //转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
