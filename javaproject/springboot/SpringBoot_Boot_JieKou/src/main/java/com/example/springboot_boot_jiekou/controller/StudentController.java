package com.example.springboot_boot_jiekou.controller;

import com.example.springboot_boot_jiekou.bean.Student;
import com.example.springboot_boot_jiekou.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 学生表界面
     */
    @GetMapping("/success")
    public String successPage(Model model) {
        List<Student> students = studentService.getStudent();
        model.addAttribute("students", students);
        return "/success";
    }

    /**
     * 删除
     */
    @GetMapping("/student/delete/{id}")
    public String deletestudent(@PathVariable("id") int id) {
        studentService.removeById(id);
        return "redirect:/success";
    }

    /**
     * 添加界面
     */
    @GetMapping("/addePage")
    public String addePage() {
        return "/add";
    }

    /**
     * 添加
     */
    @PostMapping("/student/add")
    public String addstudent(Student student) {
        studentService.addstudent(student);
        return "redirect:/success";
    }

    /**
     * 修改界面
     */
    @GetMapping("/updatePage/{id}")
    public String updatePage(@PathVariable("id") Integer id, Model model) {
        Student studentId = studentService.getUpdateStudentId(id);
        model.addAttribute("studentId", studentId);
        return "update";
    }

    /**
     * 修改
     */
    @PostMapping("/student/update")
    public String updateUser(Student student) {
        studentService.updateStudent(student);
        return "redirect:/success";
    }

    /**
     * 显示下来列表
     */
//    @RestController
    @GetMapping("/show")
    public List<Student> show() {
       return studentService.getStudent();
    }

    /**
     * 查询
     */
    @GetMapping("/student/inquiry")
    public String inquirystudent(Model model, HttpServletRequest request) {
        List<Student> students = studentService.getStudent();
        List<Student> studentList = new ArrayList<>();
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birth = request.getParameter("birth");
        String studentid = request.getParameter("studentid");
        System.out.println("111" + name);
        System.out.println("222" + sex);
        System.out.println("333" + birth);
        System.out.println("444" + studentid);
        for (Student student : students) {
            if (sex == null) {
                if (student.getName().equals(name) || student.getSex().equals(sex) || student.getBirth().equals(birth) || student.getStudentid().equals(studentid)) {
                    studentList.add(student);
                }
            }
        }
        System.out.println(studentList);
        model.addAttribute("students", studentList);
        return "success";
    }
}