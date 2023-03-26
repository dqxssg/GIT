package com.example.fwq.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
    public static String simpDate(String type, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}