package kuangshenjava;

import java.util.Scanner;

public class Ifxuanzejiegou2 {
    public static void main(String[] args) {
        //判断成绩，大于60及格，否则不及格
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入成绩");
        int score=scanner.nextInt();

        if (score==100){
            System.out.println("及格");
        }else if (score<100&&score>=80){
            System.out.println("优秀");
        }else if (score<80&&score>=60){
            System.out.println("及格");
        } else if (score<60) {
            System.out.println("不及格");
        }else {
            System.out.println("成绩不合法");
        }

        scanner.close();
    }
}
