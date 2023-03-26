package kuangshenjava;

import java.util.Scanner;

public class Scannerjinjieshiyong1 {
    public static void main(String[] args) {
        //可以输入多个数字
        // 求其总和与平均数
        // 每输入一个数字用回车确认
        // 通过输入非数字来结束输入并输出执行总和
        Scanner scanner=new Scanner(System.in);

        double sum=0;
        int m=0;

        System.out.println("请输入数据");
        //通过循环判断是否还有输入，并在里面对每一次进行求和、统计
        while (scanner.hasNextDouble()){
            double x=scanner.nextDouble();
            System.out.println("你输入了第"+(m+1)+"个数据");
            m++;
            sum=sum+x;
        }
        System.out.println(m+"个数的和为"+sum);
        System.out.println(m+"个数的平均值是"+(sum/m));

        scanner.close();
    }
}
