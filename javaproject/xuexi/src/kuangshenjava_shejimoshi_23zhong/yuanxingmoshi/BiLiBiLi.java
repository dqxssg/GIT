package kuangshenjava_shejimoshi_23zhong.yuanxingmoshi;

import tanchishe.Data;

import java.util.Date;

//客户端：克隆
public class BiLiBiLi {
    public static void main(String[] args) throws CloneNotSupportedException {
        //原型对象
        Date date = new Date();
        Video v1 = new Video("Ji",date);
        Video v2 =(Video) v1.clone();
        System.out.println("v1:"+v1);
        System.out.println("v2:"+v2);
        System.out.println("=======================");
        date.setTime(123456789);
        System.out.println("v1:"+v1);
        System.out.println("v2:"+v2);
        System.out.println("=======================");
        System.out.println("v1.hashCode:"+v1.hashCode());
        System.out.println("v2.hashCode:"+v2.hashCode());
        //更改name
        System.out.println("=======================");
        v2.setName("Clone:Ji");
        System.out.println("Cloen:v2:"+v2);
    }
}
