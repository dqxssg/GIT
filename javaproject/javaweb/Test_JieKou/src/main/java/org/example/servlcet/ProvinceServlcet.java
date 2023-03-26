package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Province;
import org.example.service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/province/*")
public class ProvinceServlcet extends BaseServlet {
    protected ProvinceService provinceService = new ProvinceService();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Province> provinces = provinceService.selectAllProvince();
        String jsonString = JSON.toJSONString(provinces);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Province province = JSON.parseObject(s, Province.class);
        provinceService.provinceUpdate(province);
        resp.getWriter().write("200");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Province province = JSON.parseObject(s, Province.class);
        provinceService.provinceDeleteById(province.getId());
        resp.getWriter().write("200");
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Province province = JSON.parseObject(s, Province.class);
        provinceService.provinceAdd(province);
        resp.getWriter().write("200");
    }
}
