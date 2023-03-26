package org.example.servlcet.settlement;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Settlement;
import org.example.pojo.Waybill;
import org.example.service.SettlementService;
import org.example.service.WaybillService;
import org.example.servlcet.waybill.WaybillServlcet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/settlementAddServlcet")
public class SettlementAddServlcet extends HttpServlet {
    SettlementService settlementService = new SettlementService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Settlement settlement = JSON.parseObject(s, Settlement.class);
        settlementService.settlementAdd(settlement);
        resp.getWriter().write("200");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
