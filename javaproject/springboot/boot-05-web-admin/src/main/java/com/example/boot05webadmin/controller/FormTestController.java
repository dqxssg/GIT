package com.example.boot05webadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.font.MultipleMaster;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传测试
 */
@Controller
public class FormTestController {

    @GetMapping("/form_layouts")
    public String formLayouts() {
        return "form/form_layouts";
    }

    /**
     * MultipartFile自动封装上传过来的文件
     *
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {
        if (!headerImg.isEmpty()) {
            //保存文件到服务器，OSS服务器
            String originalFilename = headerImg.getOriginalFilename();//获得文件原名
            headerImg.transferTo(new File("D:\\桌面\\" + originalFilename));//将文件放到D盘
        }
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    String originalFilename = photo.getOriginalFilename();//获得文件原名
                    photo.transferTo(new File("D:\\桌面\\" + originalFilename));
                }
            }
        }
        return "main";
    }
}
