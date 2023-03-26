package hanshunping_zhengzebiaodashi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//分析java的正则表达式的底层实现
public class RegExp01 {
    /**
     * matcher.find()完成的任务
     * 什么是分组：比如((\\d\\d)(\\d\\d))，正则表达式种有()，表示分组，第一个()表示第一组，第二个()表示第二组
     * 比如1998，第一组：groups[2]=0,groups[3]=2
     * 比如1998，第二组：groups[4]=2,groups[5]=4
     * 1、根据指定的规则，定位满足规则的字符串
     * 2、找到后，将子字符串的开始的索引记录到mattcher对象的属性int[] groups;groups[0]=0,把该字符串的结束的索引+1的值记录到froups[1]=2;
     * 3、同时记录lidLast的值为子字符串的结束的索引+1的值即为2，即下一次执行find时，就从2开始匹配
     */

    /**
     * matcher.group(0)分析
     * 源码：
     * public String group(int goup){
     * if(first<0){
     * throw new IllegalStateException("No match found");
     * }
     * if(group<0||group>groupCount()){
     * throw new IndexOutOfBoundsException("No group"+group);
     * }
     * if((groups[group*2]==-1)||(groups[group*2+1]==-1)){
     * return null;
     * }
     * return getSubSequence(groups[group*2],groups[group*2+1]),toString();
     * }
     * 根据：groups[0]=1和groups[1]=4的记录位置，从content开始截取子字符串返回，就是[0,4)包含0不包含索引为4的位置
     */

    /**
     * 元字符（Metacharacter）转移符\\
     * 常用的转移符号："."\"*"\"+"\"()"\"$"\"/"\"\"\"?"\"[]"\"^"\"{}"
     * []可接收到的字符列表
     * eg：[qwer]表示q、w、e、r中的任意一个字符
     * [^]不可接收的字符列表
     * eg：[^qwer]除了q、w、e、r之外的任意一个字符，包括数字和特殊符号
     * -连字符
     * eg：A-Z表示单个大写字母
     * .匹配初\n以外的字符
     * eg：a..b以a开头以b结尾，中间中间包括两个任意字符的长度为4的字符串
     * 匹配输入：aabb、ajgb、a12b、a#*b等
     * \\d匹配单个数字字符串，相当于[0-9]
     * eg：\\d{3}(\\d)?包含三个或四个数字的字符串
     * 匹配输入：123、1234等
     * \\D匹配单个非数字字符，相当于[^0-9]
     * eg：\\D(\\d)*以单个非数字字符开头，后接任意个数字字符
     * 匹配输入：a、A123等
     * \\w匹配单个数字、大小写字母字符，相当于[0-9a-zA-Z]
     * eg：\\d{3}\\w{4}以三个数字字符开头的长度为7的数字字母字符串
     * 匹配输入：123abcd、12345Pe等
     * \\W匹配非单个数字、大小写字母字符，相当于[^0-9a-zA-Z]
     * eg：\\W+\\d{2}以至少一个非数字字母符开头，两个数字字符结尾的字符串
     * 匹配输入：#12、#？@10等
     */

    /**
     * 应用实例
     * 1、[a-z]说明：可以匹配a-z中的任意一个字符
     * [a-z]表示可以匹配a-z中任意一个字符
     * [0-9]表示可以匹配0-9中任意一个字符
     * 2、java正则表达式默认是区分字母大小写的，如何实现不区分大小写
     * (?i)abc表示abc都不区分大小写
     * a(?i)bc表示bc不区分大小写
     * a((?i)b)c表示只有b不区分大小写
     * Pattern pattern=Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
     * 3、[^a-z]说明：可以匹配不是a-z中的任意一个字符
     * [^A-Z]表示可以匹配不是A-Z中的任意一个字符
     * [^0-9]表示可以匹配不是0-9中的任意一个字符
     */

    /**
     * 1、[abcd]表示可以匹配abcd中的任意一个字符串
     * 2、[^abcd]表示可以匹配不是abcd中的任意一个字符
     * 3、\\d表示可以匹配0-9的任意一个数字，相当于[0-9]
     * 4、\\D表示可以匹配不是0-9中的任意一个数字，相当于[^0-9]
     * 5、\\w匹配任意英文字符、数字和下划线，相当于[a-zA-Z0-9]
     * 6、\\W相当于[^a-zA-Z0-9]
     * 7、\\s匹配任何空白字符（空格，制表符等）
     * 8、\\S匹配任何非空白字符
     * 9、.匹配出\n之外的所有字符，如果要匹配\n本身则需要使用\\.
     */

    public static void main(String[] args) {
        //元字符-字符匹配符
        //演示字符串匹配符的使用
        RegRheory01();
        RegRheory02();
        RegRheory03();
        RegRheory04();
    }

    private static void RegRheory01() {
        System.out.println("======RegRheory01()======");
        //1
        String content = "1998年12月8日，第二代Java平台的企业版J2EE发布。" +
                "1999年6月，Sun公司发布了第二代Java平台（简称为Java2）的3个版本：J2ME（Java2 Micro Edition，Java2平台的微型版）" +
                "，应用于移动、无线及有限资源的环境；J2SE（Java 2 Standard Edition，Java 2平台的标准版），" +
                "应用于桌面环境；J2EE（Java 2Enterprise Edition，Java 2平台的企业版），应用于基于Java的应用服务器。" +
                "Java 2平台的发布，是Java发展过程中最重要的一个里程碑，标志着Java的应用开始普及";
        //目标：匹配所有的四个数字
        //说明：//d表示一个任意的数字
        System.out.println("1、(\\d\\d\\d\\d)");
        String regStr = "\\d\\d\\d\\d";
        //创建模式对象
        Pattern pattern = Pattern.compile(regStr);
        //创建匹配器
        //说明：创建匹配器matcher，按照正则表达式的规则去匹配content字符串
        Matcher matcher = pattern.matcher(content);
        //开始匹配
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        //目标：匹配所有的四个数字
        //说明：//d表示一个任意的数字
        System.out.println("2、(\\d\\d)(\\d\\d)");
        String regStr1 = "(\\d\\d)(\\d\\d)";
        //创建模式对象
        Pattern pattern1 = Pattern.compile(regStr1);
        //创建匹配器
        //说明：创建匹配器matcher，按照正则表达式的规则去匹配content字符串
        Matcher matcher1 = pattern1.matcher(content);
        //开始匹配
        while (matcher1.find()) {
            /**
             * 小结：
             * 1、如果正则表达式有()即分组
             * 2、取出匹配字符串的规则如下
             * group(0)或group()取出匹配到的所有字符串
             * group(1)取出匹配到的第一组字符串
             * group(2)取出匹配到的第二组字符串
             * ...
             * 但是分组不能越界
             */
            System.out.println(matcher1.group());
            System.out.println("第一组()匹配到的：" + matcher1.group(1));
            System.out.println("第二组()匹配到的：" + matcher1.group(2));
        }

    }

    private static void RegRheory02() {
        System.out.println("======RegRheory02()======");
        String content = "abc$(abf(123(12ab(123abc";
        //匹配(
        //.
        System.out.println(".");
        String regStr = "\\(";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("就是找(");
            System.out.println("找到(：" + matcher.group());
        }
        //\\d
        System.out.println("\\d");
        String regStr1 = "\\d{3}";//\\d\\d\\d和\\d{3}效果一样
        Pattern pattern1 = Pattern.compile(regStr1);
        Matcher matcher1 = pattern1.matcher(content);
        while (matcher1.find()) {
            System.out.println("就是找三个连在一块的");
            System.out.println("找到\\d{3}：" + matcher1.group());
        }
        //\\D
        System.out.println("\\D");
        String regStr2 = "\\D{2}";
        Pattern pattern2 = Pattern.compile(regStr2);
        Matcher matcher2 = pattern2.matcher(content);
        while (matcher2.find()) {
            System.out.println("就是找两个连在一块的非数字");
            System.out.println("找到\\D{2}：" + matcher2.group());
        }
        //\\w
        System.out.println("\\w");
        String regStr3 = "\\w{3}";
        Pattern pattern3 = Pattern.compile(regStr3);
        Matcher matcher3 = pattern3.matcher(content);
        System.out.println("就是找三个连在一起的数字字母");
        while (matcher3.find()) {
            System.out.println("找到\\w{3}：" + matcher3.group());
        }
        //\\W
        System.out.println("\\W");
        String regStr4 = "\\W{2}";
        Pattern pattern4 = Pattern.compile(regStr4);
        Matcher matcher4 = pattern4.matcher(content);
        while (matcher4.find()) {
            System.out.println("就是找连在一起的除字母数字以外的");
            System.out.println("找到\\W{2}：" + matcher4.group());
        }
    }

    private static void RegRheory03() {
        System.out.println("======RegRheory03()======");
        String content = "a11c8ABCabcAbcaBcabC";
        //[a-z]，匹配a-z之间的任意字符
        String regStr = "[a-z]";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("[a-z]：" + matcher.group());
        }
        //[0-9]，匹配0-9之间的任意字符
        String regStr1 = "[0-9]";
        Pattern pattern1 = Pattern.compile(regStr1);
        Matcher matcher1 = pattern1.matcher(content);
        while (matcher1.find()) {
            System.out.println("[0-9]：" + matcher1.group());
        }
        //不区分大小写
        //(?i)abc
        String regStr2 = "(?i)abc";
        Pattern pattern2 = Pattern.compile(regStr2);
        Matcher matcher2 = pattern2.matcher(content);
        while (matcher2.find()) {
            System.out.println("(?i)abc：" + matcher2.group());
        }
        //a(?i)bc
        String regStr3 = "a(?i)bc";
        Pattern pattern3 = Pattern.compile(regStr3);
        Matcher matcher3 = pattern3.matcher(content);
        while (matcher3.find()) {
            System.out.println("a(?i)bc：" + matcher3.group());
        }
        //a((?i)b)c
        String regStr4 = "a((?i)b)c";
        Pattern pattern4 = Pattern.compile(regStr4);
        Matcher matcher4 = pattern4.matcher(content);
        while (matcher4.find()) {
            System.out.println("a((?i)b)c：" + matcher4.group());
        }
        //a((?i)b)c
        String regStr5 = "a((?i)b)c";
        //当创建Pattern对象时，指定CASE_INSENSITIVE表示匹配是不区分大小写
        Pattern pattern5 = Pattern.compile(regStr5, Pattern.CASE_INSENSITIVE);
        Matcher matcher5 = pattern5.matcher(content);
        while (matcher5.find()) {
            System.out.println("CASE_INSENSITIVE：" + matcher5.group());
        }
        //[^A-Z]
        String regStr6 = "[^A-Z]";
        Pattern pattern6 = Pattern.compile(regStr6);
        Matcher matcher6 = pattern6.matcher(content);
        while (matcher6.find()) {
            System.out.println("[^A-Z]：" + matcher6.group());
        }
        //[^0-9]
        String regStr7 = "[^0-9]";
        Pattern pattern7 = Pattern.compile(regStr7);
        Matcher matcher7 = pattern7.matcher(content);
        while (matcher7.find()) {
            System.out.println("[^0-9]：" + matcher7.group());
        }
    }

    private static void RegRheory04() {
        System.out.println("======RegRheory04()======");
        String content = "a4564 AC\nbABC asfAG D45\n64";
        //[abcd]
        String regStr = "[abcd]";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("[abcd]：" + matcher.group());
        }
        //\\s
        String regStr1 = "\\s";
        Pattern pattern1 = Pattern.compile(regStr1);
        Matcher matcher1 = pattern1.matcher(content);
        while (matcher1.find()) {
            System.out.println("//s：" + matcher1.group());
        }
        //\\S
        String regStr2 = "\\S";
        Pattern pattern2 = Pattern.compile(regStr2);
        Matcher matcher2 = pattern2.matcher(content);
        while (matcher2.find()) {
            System.out.println("//S：" + matcher2.group());
        }
        //.
        String regStr3 = ".";
        Pattern pattern3 = Pattern.compile(regStr3);
        Matcher matcher3 = pattern3.matcher(content);
        while (matcher3.find()) {
            System.out.println(".：" + matcher3.group());
        }
    }
}