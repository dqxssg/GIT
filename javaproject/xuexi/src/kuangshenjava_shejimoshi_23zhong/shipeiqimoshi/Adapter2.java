package kuangshenjava_shejimoshi_23zhong.shipeiqimoshi;

public class Adapter2 implements NetToUsb{
    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handleRequest() {
        adaptee.request();
    }
}
