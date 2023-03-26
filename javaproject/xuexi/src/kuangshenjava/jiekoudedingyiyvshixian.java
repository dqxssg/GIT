package kuangshenjava; /**
 * 接口的作用
 *约束
 *定义一些方法
 *public abstract
 *public static final
 *接口不能被实列化，接口中没有构造方法
 *implements可以实现多个接口
 *必须要重写接口中的方法
 */



//interface定义的关键字，接口都需要有实现类

/**
 * 接口(interface)
 * 普通类，直邮具体实现
 * 抽象类，具体思想和规范（抽象方法）都有
 * 接口，只有规范
 * 接口就是规范，定义的是一组规则，体现了现实世界中“如果你是……则必须能……”的思想，如果你是天使，则必须能飞，如果你是汽车，则必须能跑
 * 接口的本质就是契约，就像法律一样，制定好大家都遵守
 * OO的精髓，是对对象的抽象，最能体现这一点的就是接口，为什么我们讨论设计模式都只针对具备了抽象的能力（比如c++、java、c#等），就是因为设计模式所研究的，实际上就是如何合理的去抽象
 */
//接口中的所有定义其实都是抽象的，默认为public abstract

    //利用接口多继承
public class jiekoudedingyiyvshixian implements jiekoudedingyiyvshixian1,jiekoudedingyiyvshixian2{
    @Override
    public void add(String name) {

    }

    @Override
    public void delete(String name) {

    }

    @Override
    public void update(String name) {

    }

    @Override
    public void query(String name) {

    }


    @Override
    public void timer() {

    }
}
