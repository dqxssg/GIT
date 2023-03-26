package kuangshenjava;

public class DiGuiJiangJie {
    /**
     *递归
     * 1、A方法调用B方法
     * 2、递归就是：A方法调用A方法，就是自己调用自己
     * 3、利用递归可以用简单的程序来解决一些复杂的问题，它通常把一个大型复杂的问题层层转化为一个与原问题相似的规模较小的问题来求解，递归策略只需要少量的程序就可以描述出解题过程所需要的多次重复计算，大大地减少了程序的代码量，递归的能力在于用有限的语句来定义对象的无限集合
     * 4、递归结构包括两个部分
     * 5、递归头：什么时候不调用自身的方法，如果没有头，将陷入死循环
     * 6、递归体：什么时候需要调用自身方法
     * 7、小计算可以，不能用大计算
     * 8、
     * 9、
     * 10、
     */
    public static void main(String[] args) {
        System.out.println(f(3));
    }











    //阶乘
    public static int f(int n){
        if (n==1){
            return 1;
        }else {
            return n*f(n-1);
        }
    }
}
