package kuangshenjava_shejimoshi_23zhong.yuanxingmoshi;

import java.util.Date;

public class BiLiBiLi_A {
    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date();
        Video_A video_a = new Video_A(date,"Ji");
        Video_A video_b = (Video_A) video_a.clone();
        System.out.println("video_a"+video_a);
        System.out.println("video_b"+video_b);
        System.out.println("================");
        date.setTime(123456789);
        System.out.println("video_a:"+video_a);
        System.out.println("video_b:"+video_b);
    }
}
