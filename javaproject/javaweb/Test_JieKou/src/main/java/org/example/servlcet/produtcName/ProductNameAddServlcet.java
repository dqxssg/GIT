package org.example.servlcet.produtcName;

import com.alibaba.fastjson.JSON;
import org.example.pojo.ProductName;
import org.example.service.ProductNameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/productNameAddServlcet")
public class ProductNameAddServlcet extends HttpServlet {

    ProductNameService productNameService = new ProductNameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        ProductName productName = JSON.parseObject(s, ProductName.class);
        productNameService.productNameAdd(productName);
        resp.getWriter().write("200");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
