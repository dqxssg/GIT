package kuangshenjava;

public class shenmeshiduotai {
    /**
     * 多态
     *
     * 动态编译：类型：可扩展性
     *
     * 即一种方法可以根据发送对象的不同而采用多种不同的行为方式
     * 一种对象的实际类型是确定的，但可以指向对象的引用的类型有很多
     *
     * 多态存在的条件：
     *          有继承关系
     *          子类重写父类的方法
     *          父类引用指向子类对象
     *
     * 注意：多态是方法的多态，属性没有多态
     * instanceof类型转换（引用类型）
     */

    /**
     * 子类重写了父类的方法，执行子类的方法
     *
     * 对象能执行哪些方法，主要看对象左边的类型和右边的类型的关系大不大
     *
     * 子类：能调用的方法都是自己的或者继承父类的
     * 父类：可以指向子类，但不能调用子类
     */

    /**
     * 多态注意事项：
     *      1、多态是方法的多态，属性没有多态
     *      2、父类和子类，有联系、类型转换异常   ClassCastException!
     *      3、存在条件：继承关系，方法需要重写，父类引用指向子类对象   Father father = new Son();
     *                  static方法（静态）属于类，它不属于实列
     *                  final常量
     *                  private方法（私有）
     *      4、
     *      5、
     *      6、
     */
}
