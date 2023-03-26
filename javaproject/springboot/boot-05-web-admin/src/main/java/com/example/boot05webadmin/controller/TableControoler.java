package com.example.boot05webadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot05webadmin.bean.User;
import com.example.boot05webadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class TableControoler {
    @Autowired
    UserService userService;

    @GetMapping("/basic_table")
    public String basic_table() {
        return "table/basic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id,
                             @RequestParam(value = "pn", defaultValue = "1") int pn,
                             RedirectAttributes redirectAttributes) {
        userService.removeById(id);
        redirectAttributes.addAttribute("pn", pn);
        return "redirect:/dynamic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn", defaultValue = "1") int pn, Model model) {
        //从数据库中查出user表中的用户进行展示
        List<User> list = userService.list();
        //分页查询数据
        Page<User> users = new Page<>(pn, 2);
        //分页查询的结果
        Page<User> page = userService.page(users, null);
        //当前页
        long current = page.getCurrent();
        //分页总页数
        long pages = page.getPages();
        //总记录数
        long total = page.getTotal();
        List<User> recods = page.getRecords();
        model.addAttribute("page", page);
        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table() {
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table() {
        return "table/editable_table";
    }
}
