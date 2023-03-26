package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Settlement;
import org.example.service.SettlementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/settlement/*")
public class SettlementServlcet extends BaseServlet {
    protected SettlementService settlementService = new SettlementService();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Settlement> customers = settlementService.selectAllSettlement();
        String jsonString = JSON.toJSONString(customers);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Settlement settlement = JSON.parseObject(s, Settlement.class);
        settlementService.settlementAdd(settlement);
        resp.getWriter().write("200");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Settlement settlement = JSON.parseObject(s, Settlement.class);
        settlementService.settlementDeleteById(settlement.getId());
        resp.getWriter().write("200");
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Settlement settlement = JSON.parseObject(s, Settlement.class);
        settlementService.settlementUpdate(settlement);
        resp.getWriter().write("200");
    }
}
