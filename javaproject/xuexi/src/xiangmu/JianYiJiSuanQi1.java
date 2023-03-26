package xiangmu;

import java.util.Scanner;

public class JianYiJiSuanQi1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入第一个数字,按回车键结束");
        int number1=scanner.nextInt();
        System.out.println("请输入运算符,按回车键结束");
        String number=scanner.next();
        System.out.println("请输入第二个数字,按回车键结束");
        int number2=scanner.nextInt();
        scanner.close();
        switch (number){
            case "+":
                System.out.println(number1+number2+"="+(number1+number2));
                break;
            case "-":
                System.out.println(number1-number2+"="+(number1-number2));
                break;
            case "*":
                System.out.println(number1*number2+"="+(number1*number2));
                break;
            case "/":
                System.out.println(number1/number2+"="+(number1/number2));
                break;
        }
    }
}
