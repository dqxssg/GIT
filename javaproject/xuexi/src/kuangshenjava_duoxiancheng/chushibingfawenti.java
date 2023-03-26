package kuangshenjava_duoxiancheng;

//多个线程同时操作同一个对象

//发现问题多个线程操作同一个资源的情况下，线程不安全，数据混乱
public class chushibingfawenti implements Runnable{

    private int ticketNums=10;

    @Override
    public void run() {
        while (true){
            if (ticketNums<=0){
                break;
            }
            try {
                //数据延迟
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {

        chushibingfawenti chushibingfawenti=new chushibingfawenti();

        new Thread(chushibingfawenti,"小明").start();
        new Thread(chushibingfawenti,"小红").start();
        new Thread(chushibingfawenti,"小蓝").start();
    }
}
