package kuangshenjava_duoxiancheng;

//创建线程方法：实现Runnable接口，重写run方法，执行线程需要丢入runnable接口实现类，调用start方法
public class shixian_Runnable implements Runnable{
    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 2; i++) {
            System.out.println("我是在看代码——————————");
        }
    }

    public static void main(String[] args) {
        //创建runnable接口的实现类对象
        shixian_Runnable shixian_runnable = new shixian_Runnable();
        //创建线程对象，通过线程对象来开启我们的线程
        new Thread(shixian_runnable).start();

        for (int i = 0; i < 10; i++) {
            System.out.println("我在学习多线程————————");
        }
    }
}
