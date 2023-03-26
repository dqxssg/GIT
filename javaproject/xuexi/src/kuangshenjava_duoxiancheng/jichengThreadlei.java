package kuangshenjava_duoxiancheng;

public class jichengThreadlei extends Thread{
    /**
     * Thread
     * 自定义线程继承Thread类
     * 重写run()方法，编写线程执行体
     * 创建线程对象，调用start()方法启动线程
     */

    /**
     * 总结：注意，线程开启不一定立即执行，由cpu调度执行
     *
     */

    public static void main(String[] args) {
        //main线程，主线程

        //创建一个线程对象
        jichengThreadlei jichengThreadlei=new jichengThreadlei();
        //调用start()方法开启线程
        jichengThreadlei.run();


        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习Java"+i);
        }
    }

    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程"+i);
        }
    }


}
