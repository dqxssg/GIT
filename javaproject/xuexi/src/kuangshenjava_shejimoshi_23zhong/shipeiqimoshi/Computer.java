package kuangshenjava_shejimoshi_23zhong.shipeiqimoshi;

//客户端类:想上网，插不上网线
public class Computer {
    //电脑需要连接上转换器才可以上网
    public void net(NetToUsb adapter) {
        //上网的具体实现，找一个转接头
        adapter.handleRequest();
    }

    public static void main(String[] args) {
        //电脑，网线适，配器
        Computer computer = new Computer();
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter();
        Adapter2 adapter2 = new Adapter2(adaptee);

        computer.net(adapter);
        computer.net(adapter2);
    }
}
