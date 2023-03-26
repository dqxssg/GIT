package kuangshenjava_shejimoshi_23zhong.dongtaidailimoshi;

import kuangshenjava_shejimoshi_23zhong.jingtaidailimoshi.UserService;
import kuangshenjava_shejimoshi_23zhong.jingtaidailimoshi.UserServiceImpl;

public class Client {
    public static void main(String[] args) {
        //真是角色
        UserServiceImpl userService = new UserServiceImpl();
        //代理角色（现在没有）
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        //设置要代理的对象
        proxyInvocationHandler.setTarget(userService);
        //动态生成代理类
        UserService proxy = (UserService) proxyInvocationHandler.getProxy();
        proxy.add();
        proxy.delete();
    }
}
