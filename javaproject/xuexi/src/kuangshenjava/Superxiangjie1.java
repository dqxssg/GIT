package kuangshenjava;

public class Superxiangjie1 extends Superxiangjie{
    //子类
    private String name="bbb";
    public Superxiangjie1(){
        //隐藏代码，调用父类的无参构造
        //调用父类的构造器，必须放在子类构造器的第一行
        super();
        System.out.println("无参构造");
    }
    public void text(String name){
        System.out.println(name);//ccc
        System.out.println(this.name);//bbb
        System.out.println(super.name);//aaa
    }
}
