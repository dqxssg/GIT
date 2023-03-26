package kuangshenjava;

/**
 * 内部类
 * 内部类就是在一个类的内部在定义一个类，比如：A类中定义一个B类，那么B类相对A类来说就称为内部类，而A类相对B类来说就是外部类了
 * 成员内部类
 * 静态内部类
 * 局部内部类
 * 匿名内部类
 */

//一个java类中可以有多个class类，但是只有一个public class类

public class nzhongneibulei {
    public static void main(String[] args) {
        nzhongneibulei1 nzhongneibulei1= new nzhongneibulei1();
        //通过这个外部类来实例化内部类
        nzhongneibulei1.Inner inner = nzhongneibulei1.new Inner();
        inner.getID();
        //没有名字初始化值类，不用将实例保存到变量中
        new Apple().eat();
    }
}
class Apple{
    public void  eat(){
        System.out.println("1");
    }
}
