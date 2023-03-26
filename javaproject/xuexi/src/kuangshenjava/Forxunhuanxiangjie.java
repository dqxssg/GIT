package kuangshenjava;

public class Forxunhuanxiangjie {
    public static void main(String[] args) {
        //初始化条件
        int a=1;
        while (a<=10){
            //循环体
            System.out.println(a);
            //迭代
            a+=2;
        }
        System.out.println("while循环结束");

        //for循环语句是支持迭代的一种通用结构，是最有效、最灵活的循环结构
        for (int i = 1; i <=10; i++) {
            System.out.println(i);
        }
        System.out.println("for循环结束");

        /**
         * 关于for循环的注意点
         * 最先执行初始化步骤，可以声明一种类型，但可以初始化一个或者多个环境控制变量，但可以是空的
         * 然后，检查表达式的值，如果为true，循环体被执行，如果是false，循环体终止，开始执行循环体后页面的语句
         * 执行一次循环后，更新循环控制变量（迭代因子控制环境变量的增减）
         * 再次检查表达式，循环执行上面的过程
         */

        int ou=0,ji=0;
        for (int j = 0; j < 100; j++) {
            //偶数
            if (j%2==0){
                ou+=1;
                //奇数
            }else {
                ji+=1;
            }
        }
        System.out.println(ou);
        System.out.println(ji);

        //用for循环输出1到1000之间被5整除的数，并且每行输出3个
        for (int k = 0; k <= 1000; k++) {
            if (k%5==0){
                System.out.print(k+"\t");
            }
            if (k%(5*3)==0){
                //换行
                //System.out.println();
                System.out.print("\n");
            }
            //println   输出完全换行
            //print   输出完不会换行
        }

    }

}
