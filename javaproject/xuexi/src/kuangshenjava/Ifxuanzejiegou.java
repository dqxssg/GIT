package kuangshenjava;

import java.util.Scanner;

public class Ifxuanzejiegou {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入内容");
        String s=scanner.nextLine();

        //equals:判断字符串是否相等
        if (s.equals("Hello")){
            System.out.println(s);
        }else {
            System.out.println("End");
        }


        scanner.close();
    }
}
