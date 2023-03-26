package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.ProductName;
import org.example.service.ProductNameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/produtcName/*")
public class ProdutcNameServlcet extends BaseServlet {
    protected ProductNameService productNameService = new ProductNameService();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductName> productNames = productNameService.selectAllProductName();
        String jsonString = JSON.toJSONString(productNames);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        ProductName productName = JSON.parseObject(s, ProductName.class);
        productNameService.productNameUpdate(productName);
        resp.getWriter().write("200");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        ProductName productName = JSON.parseObject(s, ProductName.class);
        productNameService.productNameDeleteById(productName.getId());
        resp.getWriter().write("200");
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        ProductName productName = JSON.parseObject(s, ProductName.class);
        productNameService.productNameAdd(productName);
        resp.getWriter().write("200");
    }
}
