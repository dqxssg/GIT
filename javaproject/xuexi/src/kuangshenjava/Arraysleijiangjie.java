package kuangshenjava;

import java.util.Arrays;

public class Arraysleijiangjie {
    /**
     * Arrays类
     * 1、数组的工具类Java.util.Arrays
     * 2、由于数组对象本身并没有声明方法可以提供调用，但API中提供了一个工具类Arrays供我们使用，从而可以对数据对象进行一些基本的操作
     * 3、查看JDK文档
     * 4、Arrays类中的方法都是static修饰的静态方法，在使用的时候可以直接使用类名进行调用，而“不用”使用对象来调用（注意：是”不用“而不是”不能“）
     * 5、具有以下常用功能：
     *给数组赋值：通过fill方法
     *对数组排序：通过sort方法，按升序
     *比较数组：通过equals方法比较数组中元素值是否相等
     *查找数组元素：通过binarySearch方法能对排序好的数组进行二分查找法操作
     */
    public static void main(String[] args) {
        int[] a={4545,5415,456546,56465,5641};

        System.out.println(a);//输出为：[I@776ec8df
        System.out.println("===========================");

        //打印打印数组元素Arrays.toString()
        System.out.println(Arrays.toString(a));
        System.out.println("===========================");

        printArrays(a);
        System.out.println("===========================");


        //数组进行排序   升序
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println("===========================");

        //数组填充
        Arrays.fill(a,1,2,0);
        System.out.println(Arrays.toString(a));
        System.out.println("===========================");

    }
    public static void printArrays(int[] a){
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                System.out.print("[");
            }
            if (i==a.length-1){
                System.out.print(a[i]+"]");
            }else {
                System.out.print(a[i] + ", ");
            }
        }
    }
}
