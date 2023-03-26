package kuangshenjava_duoxiancheng;

import java.util.concurrent.*;

/**
 * 实现Callable接口（扩充）
 * 实现Callable接口，需要返回值类型
 * 重写call方法，需要抛出异常
 * 创建目标对象、创建执行服务：ExecutorService ser = Executors.newFixedThreadPool(1);
 * 提交执行：Future<Boolean>result1 = ser.submit(t1);
 * 获取结果：boolean r1 = result.get();
 * 关闭服务：ser.shutdownNow();
 */

public class shixianCallablejiekou implements Callable<Boolean> {

    private String url;
    private String name;
    public shixianCallablejiekou(String url, String name){
        this.name=name;
        this.url=url;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownloader webDownloader=new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件"+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        shixianCallablejiekou a=new shixianCallablejiekou("https://blog.kuangstudy.com/usr/themes/handsome/url/img/sj/1.jpg","1.jpg");
        shixianCallablejiekou b=new shixianCallablejiekou("https://blog.kuangstudy.com/usr/themes/handsome/url/img/sj/2.jpg","2.jpg");
        shixianCallablejiekou c=new shixianCallablejiekou("https://blog.kuangstudy.com/usr/themes/handsome/url/img/sj/3.jpg","3.jpg");

        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> aa = ser.submit(a);
        Future<Boolean> bb = ser.submit(b);
        Future<Boolean> cc = ser.submit(c);
        //获取结果
        boolean aaa = aa.get();
        boolean bbb = bb.get();
        boolean ccc = cc.get();
        //关闭服务
        ser.shutdownNow();
    }
}
