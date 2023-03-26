package kuangshenjava;

public class Leixingzhuanhuan {
    public static void main(String[] args){
        //操作比较大的数字的时候，注意溢出问题
        //JDK7新特性，数字之间可以不用下划线分割
        int money=10_0000_0000;
        int years=20;
        int total=money*years;//-1474836480，计算时候溢出了
        long total2=money*years;//默认是int，转换之前已经问题

        long total3=money*((long)years);//先把一个数转换成long
        System.out.println(total3);


    }
}
