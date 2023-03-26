package org.example.servlcet.waybill;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Waybill;
import org.example.service.WaybillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/waybillDeleteByIdServlcet")
public class WaybillDeleteByIdServlcet extends HttpServlet {
    WaybillService waybillService = new WaybillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Waybill waybill = JSON.parseObject(s, Waybill.class);
        waybillService.waybillDeleteById(waybill.getId());
        resp.getWriter().write("200");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理POST求情乱码问题
        req.setCharacterEncoding("UTF-8");
        this.doGet(req, resp);
    }
}
