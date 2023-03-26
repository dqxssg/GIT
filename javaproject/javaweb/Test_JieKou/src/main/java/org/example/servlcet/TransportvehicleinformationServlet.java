package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Transportvehicleinformation;
import org.example.service.TransportvehicleinformationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/transportvehicleinformationServlet/*")
public class TransportvehicleinformationServlet extends BaseServlet {
    TransportvehicleinformationService transportvehicleinformationService = new TransportvehicleinformationService();

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transportvehicleinformation> transportvehicleinformations = transportvehicleinformationService.selectAll();
        String s = JSON.toJSONString(transportvehicleinformations);
        response.setContentType("text/json;charset=utd-8");
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
        Transportvehicleinformation transportvehicleinformation = JSON.parseObject(s, Transportvehicleinformation.class);
        transportvehicleinformationService.add(transportvehicleinformation);
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
        Transportvehicleinformation transportvehicleinformation = JSON.parseObject(s, Transportvehicleinformation.class);
        transportvehicleinformationService.update(transportvehicleinformation);
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
        Transportvehicleinformation transportvehicleinformation = JSON.parseObject(s, Transportvehicleinformation.class);
        transportvehicleinformationService.deleteById(transportvehicleinformation.getId());
        response.getWriter().write("200");
    }
}
