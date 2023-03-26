package kuangshenjava;

/**
 * super注意点：
 *          1、super调用父类的构造方法，必须在构造方法的第一个
 *          2、super必须只能出现再子类的方法或者构造方法中
 *          3、super和this不能同时调用构造方法
 * super和this的区别：
 *          代表的对象不同：
 *                  this：本身调用这个对象
 *                  super：代表父类对象的调用
 *          前提不同：
 *                  this：没有继承也可以使用
 *                  super：只能在继承条件才可以使用
 *          构造方法不同：
 *                  this：本类的构造
 *                  super：父类的构造
 */





public class Superxiangjie {
    //父类
    public Superxiangjie(){
        System.out.println("无参构造");
    }
    public String name="aaa";
}
