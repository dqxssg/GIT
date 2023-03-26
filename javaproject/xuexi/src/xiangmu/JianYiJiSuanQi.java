package xiangmu;

import java.util.Scanner;

public class JianYiJiSuanQi {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入第一个数字,按回车键结束");
        int numa=scanner.nextInt();
        System.out.println("请输入运算符，按回车键结束");
        String a=scanner.next();
        System.out.println("请输入第二个数字，按回车键结束");
        int numb=scanner.nextInt();
        scanner.close();
        switch (a){
            case "+":
                System.out.println(numa+a+numb+"="+(numa+numb));
                break;
            case "-":
                System.out.println(numa+a+numb+"="+(numa-numb));
                break;
            case "*":
                System.out.println(numa+a+numb+"="+(numa*numb));
            break;
            case "/":
                System.out.println(numa+a+numb+"="+(numa/numb));
                break;
        }
    }
}
