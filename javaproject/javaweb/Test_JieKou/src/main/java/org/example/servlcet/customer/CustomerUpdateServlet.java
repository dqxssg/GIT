package org.example.servlcet.customer;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Customer;
import org.example.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/customerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
    CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接收数据
        //获取请求数据
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        //将JSON字符串转为Java对象
        Customer customer = JSON.parseObject(s, Customer.class);
        //2、调用Service修改
        customerService.customerUpdate(customer);
        //3、响应成功标识
        resp.getWriter().write("200");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理POST求情乱码问题
        req.setCharacterEncoding("UTF-8");
        this.doGet(req, resp);
    }
}
