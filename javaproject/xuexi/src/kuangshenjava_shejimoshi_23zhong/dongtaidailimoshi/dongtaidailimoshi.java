package kuangshenjava_shejimoshi_23zhong.dongtaidailimoshi;

/**
 * 动态代理模式
 * 动态代理和静态代理角色一样
 * 动态代理的代理类是动态生成的，不是我们直接写好的
 * 动态代理分为两大类：（基于接口的动态代理和基于类的动态代理）
 * 1、基于接口的动态代理（JDK动态代理）
 * 2、基于类的动态代理（cglib）
 * 3、JAVA字节码实现：JAVAssist（加入开源代码JBoss应用服务器项目通过JAVAssist对字节码操作为JSBoss实现动态AOP框架，简单快速）
 *了解两个类：
 * 1、Proxy：代理
 * 2、invocationHandler：（接口）调用处理程序
 * 好处：
 * 1、可以是真实角色的操作更加纯粹，不用去关注一些公共的业务
 * 2、公共也就是交给代理角色，实现类业务的分工
 * 3、公共业务发生扩展的时候，方便集中管理
 * 4、一个动态代理类代理的是一个接口，一般就是对应的一类业务
 * 5、一个动态代理类可以代理多个类，只要是实现了同一个接口即可
 */
public class dongtaidailimoshi {
}
