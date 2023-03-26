package kuangshenjava_shejimoshi_23zhong.chouxianggongchangmoshi;

public class xiaomiIrouter implements Irouter {
    @Override
    public void start() {
        System.out.println("开启小米路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米路由器");
    }

    @Override
    public void openwifi() {
        System.out.println("小米路由器打开wifi");
    }

    @Override
    public void shutdownwifi() {
        System.out.println("小米路由器关闭wifi");
    }
}
