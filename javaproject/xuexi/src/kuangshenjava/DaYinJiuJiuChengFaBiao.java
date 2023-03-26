package kuangshenjava;

public class DaYinJiuJiuChengFaBiao {
    public static void main(String[] args) {

        /**
         * 1、我们先打印第一列
         * 2、把固定的1在用一个循环包起来
         * 3、去掉重复项（i<=j）
         * 4、调整样式
         * 5、
         */

        for (int j = 1; j <= 9; j++) {
            for (int i = 1; i <= j; i++) {
                System.out.print(j+"*"+i+"="+(j*i)+"\t");
            }
            System.out.println();
        }
    }
}
