package kuangshenjava_duoxiancheng;

import java.sql.SQLOutput;

/**
 * 线程休眠
 * sleep（时间）指定当前线程阻塞的毫秒数
 * sleep存在异常ImterruptedException
 * sleep时间达到后线程进入就绪状态
 * sleep可以模拟网络延迟时，倒计时等
 * 每一个对象都有一个锁，sleep不会释放锁
 */
public class xianchengxiumian_sleep implements Runnable {

    private int ticketNums = 0;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums);
        }
    }


    public static void main(String[] args) {
        xianchengxiumian_sleep xianchengxiumian_sleep=new xianchengxiumian_sleep();
        new Thread(xianchengxiumian_sleep,"小王").start();

    }
}

