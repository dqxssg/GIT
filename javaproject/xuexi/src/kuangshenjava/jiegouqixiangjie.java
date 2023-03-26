package kuangshenjava;

public class jiegouqixiangjie {

    /**
     * 构造器详解
     * 构造器：和类名相同，没有返回值
     * 作用：new本质在调用构造方法，初始化对象的值
     * 注意点：定义有参构造之后，如果想使用无参构造，显示的定义一个无参构造
     *
     */

    public static void main(String[] args) {
        //new   实例化一个对象
        jiegouqixiangjie1 jiegouqixiangjie1 = new jiegouqixiangjie1();
        System.out.println(jiegouqixiangjie1.name);
    }
}
