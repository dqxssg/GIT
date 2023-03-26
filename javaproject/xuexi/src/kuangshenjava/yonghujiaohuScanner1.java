package kuangshenjava;

import java.util.Scanner;


/**
 * nextLine()
 * 1、以Enter为结束符，也就是说nextLine()方法返回的是输入回车之前的所有字符
 * 2、可以获得空白
 */
public class yonghujiaohuScanner1 {
    public static void main(String[] args) {
        //从键盘接受数据
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数据：");

        String str=scanner.nextLine();

        System.out.println("输出的内容为："+str);

        scanner.close();
    }
}
