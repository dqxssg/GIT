package com.example.boot05webadmin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class City {
    private int id;
    private String name;
    private String state;
    private String country;
}
