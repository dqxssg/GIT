package com.example.boot05web01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTestController {
    @GetMapping("/view")
    public String view(Model model) {
        //Model中的数据会被放到请求域中request.setAttribute("a",a)
        model.addAttribute("code", 200);
        model.addAttribute("link", "www.baidu.com");
        return "success";
    }

}
