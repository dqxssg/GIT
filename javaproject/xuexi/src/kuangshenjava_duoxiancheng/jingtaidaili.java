package kuangshenjava_duoxiancheng;

/**
 * 静态代理模式总结
 * 真是对象和代理对象都要实现同一个接口
 * 代理对象要代理真实角色
 *
 * 好处
 * 代理对象可以做很多真是对象做不了的事情
 * 真实对象专注做自己的事情
 */

public class jingtaidaili {
    public static void main(String[] args) {
        You you = new You();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I LOVE YOU");
            }
        }).start();

        new WdeeingCompany().HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

//真实角色
class You implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("要结婚了");
    }
}

//代理角色
class WdeeingCompany implements Marry {

    //代理真是目标对象
    private Marry target;

    public void WdeeingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        //真实对象
        this.target.HappyMarry();
        after();
    }

    private void after() {
        System.out.println("结婚之后，收尾款");
    }

    private void before() {
        System.out.println("结婚之前，布置现场");
    }
}
