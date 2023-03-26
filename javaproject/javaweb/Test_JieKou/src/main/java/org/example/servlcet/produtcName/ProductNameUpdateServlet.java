package org.example.servlcet.produtcName;

import com.alibaba.fastjson.JSON;
import org.example.pojo.ProductName;
import org.example.pojo.User;
import org.example.service.ProductNameService;
import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/productNameUpdateServlet")
public class ProductNameUpdateServlet extends HttpServlet {
    ProductNameService productNameService = new ProductNameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        ProductName productName = JSON.parseObject(s, ProductName.class);
        productNameService.productNameUpdate(productName);
        resp.getWriter().write("200");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
