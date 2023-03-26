package kuangshenjava;

public class zizengzijianyunsuanfuchushimathlei {
    public static void main(String[] args) {
        //++自增      --自减       一元运算符

        int a=3;


        int b=a++;//执行完这行代码后，先给b赋值，再自增   a=a+1
        int c=++a;//执行完这行代码前，先自增，再给c赋值   a=a+1



        System.out.println(a);
        System.out.println(b);
        System.out.println(c);



        //幂运算
        double pow = Math.pow(2, 3);
        System.out.println(pow);
    }
}
