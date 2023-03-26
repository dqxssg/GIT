package kuangshenjava;

/**
 * 重写：需要有继承关系，子类重写父类的方法
 * 1、方法名必须相同
 * 2、参数列表必须相同
 * 3、修饰符：范围可以扩大但不能缩小（public>Protected>Default>private）
 * 4、抛出的异常：范围可以缩小但不能扩大（ClassNotFoundExcePtion<ExcePtion）
 *
 * 重写，子类的方法和父类必须一致，但方法体不同
 *
 * 为什么需要重写：父类的功能，子类不一定需要，或者不一定满足
 */
public class fangfachongxie {
    //静态的方法和非静态的方法区别很大
    //非静态：重写
    public static void main(String[] args) {

        //方法的调用只和左边定义的数据类型有关
        fangfachongxie1 fangfachongxie1 = new fangfachongxie1();
        fangfachongxie1.text();

        //父类的引用指向了子类
        //子类重写了父类的方法
        fangfachongxie2 fangfachongxie2 = new fangfachongxie1();
        fangfachongxie2.text();
    }
}
