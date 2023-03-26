package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.bean.Car;
import org.example.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody

/**
 * @RestController包含@Controller和@ResponseBody
 * @Slf4j日志，用log.info();
 */
@RestController
@Slf4j
public class HelloController {
    @Autowired
    Car car;
    @Autowired
    Person person;

    @RequestMapping("/hello")
    public Person person() {
        return person;
    }

    /**
     *
     * @return
     */
//    @RequestMapping("/hello")
//    public Car car() {
//        return car;
//    }

    /**
     *
     * @return
     */
//    @RequestMapping("/hello")
//    public String handle01() {
//        return "Hello spring Boot2";
//
//    }
}
