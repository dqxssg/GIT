package kuangshenjava_shejimoshi_23zhong.chouxianggongchangmoshi;

//路由器接口
public interface Irouter {
    //开机
    void start();

    //关机
    void shutdown();

    //打开WIFI
    void openwifi();

    //关闭wifi
    void shutdownwifi();
}
