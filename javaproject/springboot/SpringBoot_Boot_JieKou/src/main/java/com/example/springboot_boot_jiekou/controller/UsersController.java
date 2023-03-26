package com.example.springboot_boot_jiekou.controller;

import com.example.springboot_boot_jiekou.bean.Users;
import com.example.springboot_boot_jiekou.service.StudentService;
import com.example.springboot_boot_jiekou.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {
    @Autowired
    UsersService userService;
    @Autowired
    StudentService studentService;

    /**
     * 登录界面
     *
     * @return
     */
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    /**
     * 登录验证密码
     *
     * @param users
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String loginSuccess(Users users, Model model) {
        Users byUserName = userService.getByUserName(users.getUsername(), users.getPassword());
        if (byUserName != null) {
            return "redirect:/success";
        } else {
            model.addAttribute("msg", "账号或者密码有误");
            return "login";
        }
    }
}