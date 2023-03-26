package com.example.springboot_boot_jiekou.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zong {
    private int code;
    private String msg;
    private Object rows;
}
