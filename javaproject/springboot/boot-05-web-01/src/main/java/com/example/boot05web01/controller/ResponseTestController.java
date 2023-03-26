package com.example.boot05web01.controller;

import com.example.boot05web01.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ResponseTestController {

    /**
     * 1、浏览器发送请求直接返回xml
     * 2、如果是ajax请求返回json
     * 3、如果app发送请求返回自定义协议数据
     */
    /**
     * 步骤：
     * 1、添加自定义的MessageConverter进系统底层
     * 2、系统底层就会统计出所有MessageConverter能操作哪些类型
     * 3、客户端内容协商
     */
    @ResponseBody//利用返回值处理器的消息转换器进行处理
    @GetMapping(value = "/test/person")
    public Person getPerson() {
        Person person = new Person();
        person.setUsername("zhangsan");
        person.setAge(18);
        person.setBirth(new Date());
        return person;
    }
}
