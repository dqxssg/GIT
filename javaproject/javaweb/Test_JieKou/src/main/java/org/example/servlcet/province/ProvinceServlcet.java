package org.example.servlcet.province;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Customer;
import org.example.pojo.Province;
import org.example.service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlcet")
public class ProvinceServlcet extends HttpServlet {
    protected ProvinceService provinceService = new ProvinceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Province> provinces = provinceService.selectAllProvince();
        String jsonString = JSON.toJSONString(provinces);
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
