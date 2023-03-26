package org.example.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLTest1 {
    public static void main(String[] args) throws Exception {
        String usernamer = "张三";
        //1、URL编码
        System.out.println("1、URL编码");
        String encode = URLEncoder.encode(usernamer, "utf-8");
        System.out.println(encode);
        //2、URL解码
        System.out.println("2、URL解码");
//        String decode = URLDecoder.decode(encode, "utf-8");
        String decode = URLDecoder.decode(encode, "ISO-8859-1");//Tomcat将URL数据类型转换为ISO-8859-1类型
        System.out.println(decode);
        //3、转换为字节数据，编码
        System.out.println("3、转换为字节数据，编码");
        byte[] bytes = decode.getBytes("ISO-8859-1");
        for (byte aByte : bytes) {
            System.out.print(aByte+" ");
        }
        System.out.println();
        //4、将字节数组转为字符串，解码
        System.out.println("4、将字节数组转为字符串，解码");
        String s = new String(bytes, "utf-8");
        System.out.println(s);
    }
}
