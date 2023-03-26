package kuangshenjava;

public class sanzhongchushihuajineicunfenxi {
    /**
     * java内存
     * 堆：存放new的对象和数组，可以被所有的线程共享，不存放别的对象引用
     * 栈：存放基本变量类型（会包含这个基本类型的具体数量），引用对象的变量（会存放这个引用在堆里面的具体地址）
     * 方法区：可以被所有的线程共享，包含了所有的class和static变量
     */

    /**
     * 三种初始化：
     * int[] a= {1,2,3};
     * Man[] mans = {new Man(1,1),new Man(2,2)};
     *
     * 静态初始化：
     * int[] a = new int[2];
     * a[0] = 1;
     * a[1] = 2;
     *
     * 动态初始化数组的默认初始化：
     * 数组是引用类型，它的元素相同于类型的实例化变量，因此数组已经分配空间，
     * 其中的每个元素被按照实例变量的方式初始化
     *
     */
    public static void main(String[] args) {
        //静态初始化:创建并赋值
        int[] a={1,2};
        System.out.println(a[0]);
        //动态初始化
        int[] b=new int[10];
        b[0]=10;
        System.out.println(b[0]);
        System.out.println(b[1]);
    }
}
