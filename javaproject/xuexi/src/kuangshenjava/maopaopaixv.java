package kuangshenjava;

import java.util.Arrays;

public class maopaopaixv {
    /**
     * 冒泡排序
     * 冒泡排序是最出名的排序算法之一，总共有八大排序
     * 冒泡的代码还是相当简单，两层循环，外层冒泡轮数，里层依次比较
     * 我们看到嵌套循环，应该立马就可以得出这个算法的时间复杂度为0（n2）
     */

    public static void main(String[] args) {
        int[] a={564,6846,68465,964854,6451};
        //调用完我们自己的排序方法以后，返回一个排序后的数组
        int[] sort=sort(a);
        System.out.println(Arrays.toString(sort));
    }

    /**
     *冒泡排序
     * 比较数组中，两个相邻的元素，如果第一个数比第二个数大，我们就交换它们的位置
     * 第一次比较，都会产生出一个最大，或者最小的数字
     * 下一轮则可以少一次排序
     * 依次循环，直到结束
     */
    public static int[] sort(int[] array){

        //临时变量
        int temp=0;
        //外层循环，判断我们这个变化走多少次
        for (int i = 0; i < array.length; i++) {
            //通过flag标识减少没有意义的比较
            boolean flag=false;
            //内层循环，比较判断两个数，如果第一个数比第二个数大，则交换
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j+1]<array[j]){
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                    flag=true;
                }
            }
            if (flag==false){
                break;
            }
        }
        return array;
    }
}
