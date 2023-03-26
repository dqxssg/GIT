package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Waybill;
import org.example.service.WaybillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/waybill/*")
public class WaybillServlcet extends BaseServlet {
    protected WaybillService waybillService = new WaybillService();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Waybill> waybills = waybillService.selectAllWaybill();
        String jsonString = JSON.toJSONString(waybills);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Waybill waybill = JSON.parseObject(s, Waybill.class);
        waybillService.waybillAdd(waybill);
        resp.getWriter().write("200");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Waybill waybill = JSON.parseObject(s, Waybill.class);
        waybillService.waybillDeleteById(waybill.getId());
        resp.getWriter().write("200");
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Waybill waybill = JSON.parseObject(s, Waybill.class);
        waybillService.waybillUpdate(waybill);
        resp.getWriter().write("200");
    }
}
