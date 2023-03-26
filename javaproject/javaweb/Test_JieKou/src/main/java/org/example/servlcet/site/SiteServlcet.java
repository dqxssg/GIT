package org.example.servlcet.site;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Customer;
import org.example.pojo.Site;
import org.example.service.SiteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/siteServlcet")
public class SiteServlcet extends HttpServlet {
    protected SiteService siteService = new SiteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Site> sites = siteService.selectAllSite();
        String jsonString = JSON.toJSONString(sites);
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
