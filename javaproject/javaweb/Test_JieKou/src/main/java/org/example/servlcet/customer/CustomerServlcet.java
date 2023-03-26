package org.example.servlcet.customer;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Customer;
import org.example.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customerServlcet")
public class CustomerServlcet extends HttpServlet {
    protected CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = customerService.selectAllCustomer();
        String jsonString = JSON.toJSONString(customers);
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
