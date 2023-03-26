package kuangshenjava_shejimoshi_23zhong.jingtaidailimoshi;

public class Client {
    public static void main(String[] args) {
        //房东要租房子
        Host host = new Host();
        //代理：中介方（代理一般会有一些其他操作）
        Proxy proxy = new Proxy(host);
        //不用面对房东，直接找中介租房
        proxy.rent();
        System.out.println("======加深======");
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy userServiceProxy = new UserServiceProxy();
        userServiceProxy.setUserService(userService);
        userServiceProxy.add();
        userServiceProxy.delete();
        userServiceProxy.update();
        userServiceProxy.query();
    }
}
