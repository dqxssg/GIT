package kuangshenjava;

public class xiabiaoyuejiejixiaojie {
    /**
     * 数组的四个基本特点
     * 1、其长度是确定的，数组一旦被创建，它的大小就是不可以改变的
     * 2、其元素必须是相同类型，不允许出现混合类型
     * 3、数组中的元素可以是任何数据类型，包括基本类型和引用类型
     * 4、数组变量属于引用类型，数组也可以看成是对象，数组中的每个元素相当于该对象的成员变量
     * 5、数组本身就是对象，java中对象是在堆中的，因此数组无论保存原始类型还是其他对象类型，数组对象本身是在堆中
     */

    /**
     * 数组边界
     * 1、下标的合法区间：[0,length-1]，如果越界就会报错：
     * public static void main(String[] args) {
     *          int[]  a = new int[2];
     *          System.out.println(a[2]);
     *     }
     * 2、ArrayIndxOutOfBoundsExCeption:数组下标越界异常!
     * 3、小结：
     *          数组是相同数据类型（数据类型可以为任意类型）的有序集合
     *          数组也是对象，数组元素相当与对象的成员变量
     *          数组长度的确定，不可变，如果越界，则报：ArrayIndxOutOfBounds
     */

    public static void main(String[] args) {
        int[] a={1,2,3};
        System.out.println(a[0]);
        for (int i = 0; i <= a.length; i++) {
            System.out.println(a[i]);
        }
    }


}
