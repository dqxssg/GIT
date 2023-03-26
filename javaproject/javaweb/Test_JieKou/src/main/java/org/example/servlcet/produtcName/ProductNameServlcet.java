package org.example.servlcet.produtcName;

import com.alibaba.fastjson.JSON;
import org.example.pojo.ProductName;
import org.example.service.ProductNameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productNameServlcet")
public class ProductNameServlcet extends HttpServlet {
    ProductNameService productNameService = new ProductNameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductName> productNames = productNameService.selectAllProductName();
        String jsonString = JSON.toJSONString(productNames);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理POST求情乱码问题
        req.setCharacterEncoding("UTF-8");
        this.doGet(req, resp);
    }
}
