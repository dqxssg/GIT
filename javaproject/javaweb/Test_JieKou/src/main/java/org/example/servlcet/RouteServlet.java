package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Route;
import org.example.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RouteServlet extends BaseServlet {
    RouteService routeService = new RouteService();

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> routes = routeService.selectAll();
        String s = JSON.toJSONString(routes);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    /**
     * 添加数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Route route = JSON.parseObject(s, Route.class);
        routeService.add(route);
        response.getWriter().write("200");
    }

    /**
     * 修改数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Route route = JSON.parseObject(s, Route.class);
        routeService.update(route);
        response.getWriter().write("200");
    }

    /**
     * 删除数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = new String(reader.readLine().getBytes("ISO-8859-1"), "utf-8");
        Route route = JSON.parseObject(s, Route.class);
        routeService.deteleById(route.getId());
        response.getWriter().write("200");
    }
}
