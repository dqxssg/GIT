package kuangshenjava_shejimoshi_23zhong.chouxianggongchangmoshi;

public class huaweiIrouter implements Irouter {
    @Override
    public void start() {
        System.out.println("开启华为路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为路由器");
    }

    @Override
    public void openwifi() {
        System.out.println("华为路由器打开wifi");
    }

    @Override
    public void shutdownwifi() {
        System.out.println("华为路由器关闭wifi");
    }
}
