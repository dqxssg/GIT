package kuangshenjava_duoxiancheng;

/**
 * setPriority(int newPriority)更改线程的优先级
 * static void sieep(long millis)在指定的毫秒数内让当前正在执行的线程休眠
 * void join()等待线程终止
 * static void yield()暂停当前正在执行的线程对象，并执行其他线程
 * void interrupt()中断线程，别用这个方法
 * boolean isAlive()测试线程是否处于活动状态
 */

/**
 * 测试stop
 * 建议线程正常停止-->利用次数，不建议死循环
 * 建议使用游标位-->设置一个游标
 * 不要使用stop或者destroy等过时或者JDK不建议使用的方法
 */

public class xianchengtingzhi implements Runnable {

    //设置一个标识位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run...Thread" + i++);
        }
    }

    //设置一个公开的方法停止线程，转换标志位
    public void stop() {
        this.flag = flag;
    }


    public static void main(String[] args) {
        xianchengtingzhi xianchengtingzhi = new xianchengtingzhi();
        new Thread(xianchengtingzhi).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main" + i);
            if (i == 900) {
                //调用stop方法切换标志位，让线程停止
                xianchengtingzhi.stop();
                System.out.println("线程停止了");
            }
        }

    }
}
