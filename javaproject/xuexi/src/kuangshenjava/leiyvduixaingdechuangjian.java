package kuangshenjava;

public class leiyvduixaingdechuangjian {
    /**
     * 类与对象的创建
     *
     * 类：是一种抽象的对象类型，它是对某一类事物整体描述、定义，但是并不能代表某一个具体的事物
     *类：抽象的，需要实例化，实例化后会返回一个自己的对象
     *
     * 对象：是抽象概念的具体实列
     */
    /**
     * 创建与初始化对象
     *
     * 使用new关键字创建对象
     *
     * 使用new关键字创建的时候，除了分配内存空间之外，还会给创建好的对象进行默认的初始化以及对类中的构造器的调用
     *
     * 类中的构造器也称为构造方法，是在进行创建对象的时候必须要调用的，并且构造器有以下两个特点
     * 1、必须和类的名字相同
     * 2、必须没有返回类型，也不能写void
     */
    public static void main(String[] args) {
        leiyvduixaingdechuangjian1 xiaoming = new leiyvduixaingdechuangjian1();
        leiyvduixaingdechuangjian1 xiaohong = new leiyvduixaingdechuangjian1();

        xiaohong.name="xiaohong";
        xiaohong.age=18;

        System.out.println(xiaohong.name);
        System.out.println(xiaohong.age);

        xiaoming.name="xiaoming";
        xiaoming.age=20;

        System.out.println(xiaoming.name);
        System.out.println(xiaoming.age);
    }
}
