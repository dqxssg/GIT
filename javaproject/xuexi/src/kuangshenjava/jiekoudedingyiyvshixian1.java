package kuangshenjava;

//类，可以实现接口    implements接口
//抽象类，extends
//实现了接口的类，就必须重写接口中的类
public interface jiekoudedingyiyvshixian1 {

    //属性,默认为public static final
    int age=99;

    //接口中的所有定义其实都是抽象的，默认为public abstract
    void add(String name);
    void delete(String name);
    void update(String name);
    void query(String name);
}
