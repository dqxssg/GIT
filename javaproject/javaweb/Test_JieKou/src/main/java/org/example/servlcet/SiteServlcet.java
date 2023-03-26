package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Site;
import org.example.service.SiteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/site/*")
public class SiteServlcet extends BaseServlet {
    protected SiteService siteService = new SiteService();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Site> sites = siteService.selectAllSite();
        String jsonString = JSON.toJSONString(sites);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Site site = JSON.parseObject(s, Site.class);
        siteService.siteAdd(site);
        resp.getWriter().write("200");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Site site = JSON.parseObject(s, Site.class);
        siteService.siteDeleteById(site.getId());
        resp.getWriter().write("200");
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Site site = JSON.parseObject(s, Site.class);
        siteService.siteUpdate(site);
        resp.getWriter().write("200");
    }
}
