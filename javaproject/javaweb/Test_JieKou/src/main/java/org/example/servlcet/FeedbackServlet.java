package org.example.servlcet;

import com.alibaba.fastjson.JSON;
import org.example.pojo.Feedback;
import org.example.service.FeedbackService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/feedback/*")
public class FeedbackServlet extends BaseServlet {
    FeedbackService feedbackService = new FeedbackService();

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Feedback> feedbacks = feedbackService.selectAll();
        String s = JSON.toJSONString(feedbacks);
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
        Feedback feedback = JSON.parseObject(s, Feedback.class);
        feedbackService.add(feedback);
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
        Feedback feedback = JSON.parseObject(s, Feedback.class);
        feedbackService.update(feedback);
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
        Feedback feedback = JSON.parseObject(s, Feedback.class);
        feedbackService.deleteById(feedback.getId());
        response.getWriter().write("200");
    }
}
