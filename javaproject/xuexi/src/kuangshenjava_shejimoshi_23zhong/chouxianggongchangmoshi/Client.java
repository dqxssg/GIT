package kuangshenjava_shejimoshi_23zhong.chouxianggongchangmoshi;

public class Client {
    public static void main(String[] args) {
        System.out.println("======================================小米系列产品======================================");
        //小米工厂
        xiaomiFactory xiaomifactory = new xiaomiFactory();
        //手机
        Iphone iphone = xiaomifactory.iphone();
        iphone.start();
        iphone.shutdown();
        iphone.callup();
        iphone.sendSMS();
        //路由器
        Irouter irouter = xiaomifactory.irouter();
        irouter.start();
        irouter.shutdown();
        irouter.openwifi();
        irouter.shutdownwifi();
        System.out.println("======================================华为系列产品======================================");
        //华为工厂
        huaweiFactory huaweifactory = new huaweiFactory();
        //手机
        Iphone iphone1 = huaweifactory.iphone();
        iphone1.start();
        iphone1.shutdown();
        iphone1.callup();
        iphone1.sendSMS();
        //路由器
        Irouter irouter1 = huaweifactory.irouter();
        irouter1.start();
        irouter1.shutdown();
        irouter1.openwifi();
        irouter1.shutdownwifi();
    }
}
