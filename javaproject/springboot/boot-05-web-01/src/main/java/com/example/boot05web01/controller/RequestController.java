package com.example.boot05web01.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {
    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功...");
        request.setAttribute("code", 200);
        return "forward:/success";//转发到success请求
    }

    @GetMapping("/params")
    public String testParam(Map<String, Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        map.put("hello", "hello666");
        model.addAttribute("word", "word666");
        request.setAttribute("message", "message666");
        Cookie cookie = new Cookie("c1", "c1666");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/success";
    }

    /**
     * required = false说明该值不是必须的要传的
     *
     * @param msg
     * @param code
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/success")
    public String success(@RequestAttribute(value = "msg", required = false) String msg,
                          @RequestAttribute(value = "code", required = false) Integer code,
                          HttpServletRequest request) {
        /**
         * msg和request.getAttribute("msg");
         * code和request.getAttribute("code");
         * 他们的之都一样
         */
        Map<String, Object> map = new HashMap<>();
        Object hello = request.getAttribute("hello");
        Object word = request.getAttribute("word");
        Object message = request.getAttribute("message");
        map.put("msg", map);
        map.put("code", code);
        map.put("hello", hello);
        map.put("word", word);
        map.put("message", message);
        return String.valueOf(map);
    }
}
