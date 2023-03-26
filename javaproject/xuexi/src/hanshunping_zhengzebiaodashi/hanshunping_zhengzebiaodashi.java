package hanshunping_zhengzebiaodashi;

//正则表达式

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1、提取文章中所有的英文单词
 * 2、提取文章中所有的数字
 * 3、提取文章中所有的英文单词和数字
 * 4、提取百度热榜标题
 */
public class hanshunping_zhengzebiaodashi {
    public static void main(String[] args) {
        String content = "1、Java是一门面向对象的编程语言，" + "2、不仅吸收了C++语言的各种优点，" + "3、还摒弃了C++里难以理解的多继承、指针等概念，" + "4、因此Java语言具有功能强大和简单易用两个特征。" + "5、Java语言作为静态面向对象编程语言的代表，" + "6、极好地实现了面向对象理论，" + "7、允许程序员以优雅的思维方式进行复杂的编程。\n" + "8、Java具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动态性等特点。" + "9、Java可以编写桌面应用程序、Web应用程序、分布式系统和嵌入式系统应用程序等。";
        String content1 = "<meta name=\"keywords\" content=\"百度热搜\">\n" + "<meta name=\"keywords\" content=\"百度热搜榜\">\n" + "<meta name=\"keywords\" content=\"百度搜索排行榜\">\n" + "<meta name=\"keywords\" content=\"搜索排行榜\">\n" + "<meta name=\"keywords\" content=\"今日热搜\">\n" + "<meta name=\"keywords\" content=\"今日热点\">\n" + "<meta name=\"keywords\" content=\"排行榜\">\n" + "<meta name=\"keywords\" content=\"热搜榜\">\n" + "<meta name=\"keywords\" content=\"热词榜\">\n" + "<meta name=\"keywords\" content=\"热门话题\">\n" + "<meta name=\"keywords\" content=\"网络热点\">\n" + "<meta name=\"keywords\" content=\"实时热点\">\n" + "<meta name=\"keywords\" content=\"热门事件\">\n" + "<meta name=\"keywords\" content=\"热点\">\n" + "<meta name=\"description\" content=\"百度热搜以数亿用户海量的真实数据为基础，通过专业的数据挖掘方法，计算关键词的热搜指数，旨在建立权威、全面、热门、时效的各类关键词排行榜，引领热词阅读时代。\">\n" + "<title>百度热搜</title>\n" + "<style data-vue-ssr-id=\"22cfed39:0\">\n";
        String content2 = "私有地址（Private address）属于非注册地址，专门为组织机构内部使用。\n" + "以下列出留用的内部私有地址\n" + "A类 10.0.0.0--10.255.255.255\n" + "B类 172.16.0.0--172.31.255.255\n" + "C类 192.168.0.0--192.168.255.255";

        //提取文章中所有的英文单词
        //1、先创建一个Pattern对象，模式对象，可以理解成就是一个正则表达式对象
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        //2、创建一个匹配器对象
        //理解：解释matcher匹配器按照Pattern（模式/样式），到content文本中去匹配
        //找到就返回true否则就返回false
        Matcher matcher = pattern.matcher(content);
        //3、可以开始循环匹配
        while (matcher.find()) {
            //匹配到的内容放到matcher.group(0)
            System.out.println("提取文章中所有的英文单词:" + matcher.group(0));
        }

        //提取文章中所有的数字
        //1、先创建一个Pattern对象，模式对象，可以理解成就是一个正则表达式对象
        Pattern pattern1 = Pattern.compile("[0-9]+");
        //2、创建一个匹配器对象
        //理解：解释matcher匹配器按照Pattern（模式/样式），到content文本中去匹配
        //找到就返回true否则就返回false
        Matcher matcher1 = pattern1.matcher(content);
        //3、可以开始循环匹配
        while (matcher1.find()) {
            //匹配到的内容放到matcher.group(0)
            System.out.println("提取文章中所有的数字:" + matcher1.group(0));
        }

        //提取文章中所有的英文单词和数字
        //1、先创建一个Pattern对象，模式对象，可以理解成就是一个正则表达式对象
        Pattern pattern2 = Pattern.compile("([0-9]+)|([a-zA-Z]+)");
        //2、创建一个匹配器对象
        //理解：解释matcher匹配器按照Pattern（模式/样式），到content文本中去匹配
        //找到就返回true否则就返回false
        Matcher matcher2 = pattern2.matcher(content);
        //3、可以开始循环匹配
        while (matcher2.find()) {
            //匹配到的内容放到matcher.group(0)
            System.out.println("提取文章中所有的英文单词和数字:" + matcher2.group(0));
        }

        //提取百度热榜标题
        //1、先创建一个Pattern对象，模式对象，可以理解成就是一个正则表达式对象
        Pattern pattern3 = Pattern.compile("<meta name=\"keywords\" content=\"(\\S*)\"");
        //2、创建一个匹配器对象
        //理解：解释matcher匹配器按照Pattern（模式/样式），到content文本中去匹配
        //找到就返回true否则就返回false
        int num = 0;
        Matcher matcher3 = pattern3.matcher(content1);
        //3、可以开始循环匹配
        while (matcher3.find()) {
            //匹配到的内容放到matcher.group(1)
            System.out.println("热搜标题:" + (num++) + "、" + matcher3.group(1));
        }

        //提取IP地址
        //1、先创建一个Pattern对象，模式对象，可以理解成就是一个正则表达式对象
        Pattern pattern4 = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
        //2、创建一个匹配器对象
        //理解：解释matcher匹配器按照Pattern（模式/样式），到content文本中去匹配
        //找到就返回true否则就返回false
        Matcher matcher4 = pattern4.matcher(content2);
        //3、可以开始循环匹配
        while (matcher4.find()) {
            //匹配到的内容放到matcher.group(0)
            System.out.println("IP地址:" + matcher4.group(0));
        }
        System.out.println("==================================================================");
        System.out.println("==================================================================");
        System.out.println("请输入小写字母字符串");
        Scanner in = new Scanner(System.in);
        String input = in.next();
        //判断字符串开头是否为ab
        if (input.substring(0, 2).equals("ab")) {
            //将字符串前两位之后取出并输出
            System.out.println("替换前缀后的字符串为：ef" + input.substring(2));
            //判断字符串后缀是否为cd且前缀不为ab
        } else if (input.substring(0, 2).equals("ab") == false && input.substring(input.length() - 2, input.length()).equals("cd")) {
            //将字符串中的cd替换成gh并输出
            System.out.println("替换cd后的字符串：" + input.replace("cd", "gh"));
        } else {
            //将小写字母转换成大写字母并输出
            System.out.println("大写字母的字符串为：" + input.toUpperCase());
        }
    }
}
