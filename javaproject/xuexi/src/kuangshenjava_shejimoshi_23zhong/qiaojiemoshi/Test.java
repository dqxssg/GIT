package kuangshenjava_shejimoshi_23zhong.qiaojiemoshi;

public class Test {
    public static void main(String[] args) {
        //苹果笔记本
        Computer computer1=new Laptop(new Apple());
        computer1.info();
        //联想台式机
        Computer computer2=new Desktop(new LEGION());
        computer2.info();
    }
}
