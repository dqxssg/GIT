package org.example.servlcet.province;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Province;
import org.example.service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/provinceDeleteByIdServlcet")
public class ProvinceDeleteByIdServlcet extends HttpServlet {
    ProvinceService provinceService = new ProvinceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Province province = JSON.parseObject(s, Province.class);
        provinceService.provinceDeleteById(province.getId());
        resp.getWriter().write("200");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
