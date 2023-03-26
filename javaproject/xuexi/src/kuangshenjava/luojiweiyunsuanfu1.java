package kuangshenjava;

public class luojiweiyunsuanfu1 {
    public static void main(String[] args) {
        /**
         *
         * 位运算
         *
         * A=0011 1100
         * B=0000 1101
         *
         * A&B（与 ）  =0000 1100
         * A|B（或）  =0011 1101
         * A^B（非）   =0011 0001
         * ~B（取反）    =1100 0010
         *
         *位运算效率高
         *
         * <<右移   *2
         * >>左移   /2
         */

        System.out.println(2<<3);
        System.out.println(3<<2);
        System.out.println(2>>3);
    }
}
