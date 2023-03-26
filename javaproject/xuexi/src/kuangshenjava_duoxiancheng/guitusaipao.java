package kuangshenjava_duoxiancheng;

//模拟龟兔赛跑
public class guitusaipao implements Runnable {

    //判断者
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            //模拟兔子休息
            if (Thread.currentThread().getName().equals("兔子")&&i%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //判断比赛是否结束
            boolean flag = ganmeOver(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

    //判断是否完成比赛
    private boolean ganmeOver(int steps) {
        //判断是否有胜利者
        if (winner != null) {
            return true;
        }
        {
            if (steps >= 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner is" + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        guitusaipao guitusaipao=new guitusaipao();
        new Thread(guitusaipao,"兔子").start();
        new Thread(guitusaipao,"乌龟").start();
    }
}
