package com.example.boot05webadmin.controller;

import com.example.boot05webadmin.bean.*;
import com.example.boot05webadmin.interceptor.RedisUrlCountInterceptor;
import com.example.boot05webadmin.mapper.StudentMapper;
import com.example.boot05webadmin.service.CityService;
import com.example.boot05webadmin.service.RouteService;
import com.example.boot05webadmin.service.StudentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    RouteService routeService;
    @Autowired
    CityService cityService;

    @Resource
    StudentMapper studentMapper;
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;

    @ResponseBody
    @GetMapping("/student")
    public Student student(@RequestParam("id") int id) {
        return studentMapper.selectById(1);
    }

    /**
     * 插入数据
     *
     * @param city
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public City save(City city) {
        cityService.saveCity(city);
        return city;
    }

    /**
     * 查询数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") int id) {
        return cityService.getById(id);
    }

    /**
     * 查询数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/route")
    public Route getById(@RequestParam("id") int id) {
        return routeService.getRouteById(id);
    }

    /**
     * 查询数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/queryFromDb")
    public String queryFromDb() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        return aLong.toString();
    }

    /**
     * 进入登录界面
     *
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    /**
     * 登录成功进入主页
     *
     * @return
     */
    @PostMapping("/login")
    public String main(LoginUser loginUser, HttpSession session, Model model) {
        if (StringUtils.hasLength(loginUser.getUserName()) && "123456".equals(loginUser.getPassword())) {
            //把登录成功的用户保存起来
            session.setAttribute("loginUser", loginUser);
            //登录成功，重定向到main.html；重定向防止表单重复提交
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "错误");
            //回到登录界面
            return "/login";
        }
    }

    /**
     * 刷新界面,进入main界面
     *
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model) {
        //是否登录，拦截器，过滤器
//        Object loginUser = session.getAttribute("loginUser");
//        if (loginUser != null) {
//            return "main";
//        } else {
//            //回到登录界面
//            model.addAttribute("msg", "请登录");
//            return "login";
//        }
        //redis获取页面访问的次数
//        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
//        String s = stringStringValueOperations.get("/main.html");
//        model.addAttribute("mainCount", s);
//        System.out.println("访问次数：" + s);
        return "main";
    }
}
