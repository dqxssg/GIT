package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Customer;
import org.example.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/*")
public class CustomerServlcet extends BaseServlet {
    CustomerService customerService = new CustomerService();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = customerService.selectAllCustomer();
        String jsonString = JSON.toJSONString(customers);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接收数据
        //获取请求数据
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        //将JSON字符串转为Java对象
        Customer customer = JSON.parseObject(s, Customer.class);
        //2、调用Service添加
        customerService.customerAdd(customer);
        //3、响应成功标识
        resp.getWriter().write("200");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接收数据
        //获取请求数据
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        //将JSON字符串转为Java对象
        Customer customer = JSON.parseObject(s, Customer.class);
        //2、调用Service修改
        customerService.customerDeleteById(customer.getId());
        //3、响应成功标识
        resp.getWriter().write("200");
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
}
