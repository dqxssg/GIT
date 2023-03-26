package kuangshenjava;

public class luojiweiyunsuanfu {
    //逻辑运算符
    public static void main(String[] args) {
        //   或||   与&&   非!
        boolean a=true;
        boolean b=false;
        System.out.println("a&&b:"+(a&&b));//逻辑与运算：两个变量都为真，结果才为true
        System.out.println("a||b:"+(a||b));//逻辑或运算：两个变量有一个为真，则结果为true
        System.out.println("!(a&&b):"+!(a&&b));//逻辑非运算：如果是真则变为假，如果是假，则变为真

        //短路运算
        int c=5;
        boolean d=(c<4)&&(c++<4);
        System.out.println(c);
        System.out.println(d);

        int e=5;
        boolean f=(c++>4)&&(c<4);
        System.out.println(e);
        System.out.println(f);
    }
}
