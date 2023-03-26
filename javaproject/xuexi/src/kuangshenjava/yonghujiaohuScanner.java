package kuangshenjava;

import java.util.Scanner;


//通过Scanner类的next()与nextLine()方法获取输入的字符串，
// 在读取钱我们一般需要使用hasNext()与hasNextLine()判断是否还有输入的数据


/**
 * next()
 * 1、一定要读取到有效字符后才可以结束输入
 * 2、对输入有效字符之气遇到的空白，next()方法会自动将其去掉
 * 3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符
 * 4、next()不能得到带有空格的字符串
 */
public class yonghujiaohuScanner {
    public static void main(String[] args) {

        //创建一个扫描器对象，用于接受键盘数据
        Scanner scanner=new Scanner(System.in);

        System.out.println("使用next方法接收数据：");


        //判断用户有没有没有输入字符串
        if (scanner.hasNext()){
            //使用next方式接收
            String str=scanner.next();//程序会等待用户输入完毕
            System.out.println("输出的内容为："+str);
        }
        //凡是属于IO流的类如果不关闭会一直占用资源，用完要关掉
        scanner.close();
    }
}
