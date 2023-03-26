package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Reservation;
import org.example.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ReservationServlet extends BaseServlet {
    ReservationService reservationService = new ReservationService();

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservations = reservationService.seletAll();
        String s = JSON.toJSONString(reservations);
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
        Reservation reservation = JSON.parseObject(s, Reservation.class);
        reservationService.add(reservation);
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
        Reservation reservation = JSON.parseObject(s, Reservation.class);
        reservationService.update(reservation);
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
        Reservation reservation = JSON.parseObject(s, Reservation.class);
        reservationService.deleteById(reservation.getId());
        response.getWriter().write("200");
    }

}
