package kuangshenjava;

public class staticguanjianzixiangjie {
    private static int age;//静态变量
    private double score;//非静态变量

    public void run(){

    }
    private static void go(){

    }

    public static void main(String[] args) {
        staticguanjianzixiangjie st = new staticguanjianzixiangjie();

        System.out.println(staticguanjianzixiangjie.age);
        System.out.println("==========================================");
        System.out.println(st.age);
        System.out.println("==========================================");
        System.out.println(st.score);
        System.out.println("==========================================");
        staticguanjianzixiangjie.go();
        System.out.println("==========================================");
        go();
        System.out.println("==========================================");
    }
    {
        System.out.println("匿名代码块");
        System.out.println("==========================================");
    }
    //只执行一次
    static {
        System.out.println("静态代码块");
        System.out.println("==========================================");
    }
    public staticguanjianzixiangjie(){
        System.out.println("构造方法");
        System.out.println("==========================================");
    }
}
