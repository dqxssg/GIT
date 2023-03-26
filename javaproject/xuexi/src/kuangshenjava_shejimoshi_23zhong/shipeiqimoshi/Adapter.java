package kuangshenjava_shejimoshi_23zhong.shipeiqimoshi;


/**
 * 1、继承：类适配器，单继承
 * 2、组合：对象适配器，常用
 */

//真正的适配器，需要连接网线和usb
public class Adapter extends Adaptee implements NetToUsb{
    @Override
    public void handleRequest() {
        //可以上网了
        super.request();
    }
}
