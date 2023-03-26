package kuangshenjava_duoxiancheng;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread，实现多线程同步下载图片
public class wangtuxiazai extends Thread {
    private String url;
    private String name;

    public wangtuxiazai(String url, String name) {
        this.url = url;
        this.name = name;
    }

    //下载图片线程执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为" + name);
    }

    public static void main(String[] args) {
        wangtuxiazai a = new wangtuxiazai("https://blog.kuangstudy.com/usr/themes/handsome/url/img/sj/1.jpg", "1.jpg");
        wangtuxiazai b = new wangtuxiazai("https://blog.kuangstudy.com/usr/themes/handsome/url/img/sj/2.jpg", "2.jpg");
        wangtuxiazai c = new wangtuxiazai("https://blog.kuangstudy.com/usr/themes/handsome/url/img/sj/3.jpg", "3.jpg");

        a.start();
        b.start();
        c.start();
    }
}

//下载器
class WebDownloader {
    //下载方法
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常,downloader方法出现错误");
        }

    }
}
