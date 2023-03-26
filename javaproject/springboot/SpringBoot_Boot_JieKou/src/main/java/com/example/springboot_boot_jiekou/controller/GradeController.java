package com.example.springboot_boot_jiekou.controller;

import com.example.springboot_boot_jiekou.bean.Grade;
import com.example.springboot_boot_jiekou.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GradeController {
    @Autowired
    GradeService gradeService;

    /**
     * 成绩表
     */
    @GetMapping("/grade")
    public String gradePage(Model model) {
        List<Grade> grades = gradeService.SeleteGrade();
        model.addAttribute(grades);
        return "/grade";
    }

    /**
     * 删除
     */
    @GetMapping("grade/delete/{id}")
    public String deleteGrade(@PathVariable("id") int id) {
        gradeService.DeleteGrade(id);
        return "redirect:/grade";
    }

    /**
     * 添加界面
     */
    @GetMapping("/insertGradePage")
    public String insertGradePage() {
        return "/insertGradePage";
    }

    /**
     * 添加
     */
    @PostMapping("/insertGradePage/add")
    public String addGrade(Grade grade) {
        gradeService.InsertGrade(grade);
        return "redirect:/grade";
    }
    /**
     * 修改界面
     */
//    @GetMapping("/updqrePage")
//    public String updqrePage(){
//        return
//    }
}
