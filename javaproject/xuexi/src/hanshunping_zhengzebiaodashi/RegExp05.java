package hanshunping_zhengzebiaodashi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 捕获分组
 * <p>
 * Pattern
 * 非命名捕获，捕获匹配的子字符串。编号位零的第一个捕获是由整个正则表达式模式匹配的文本，其它捕获结果则根据左括号的顺序从一开始自动编号
 * <p>
 * ?<name>Pattern或者?'name'
 * 命名捕获，将匹配的子字符串捕获到一个组名称或编号名称中。用于name的字符串不能包含任何标点符号，并且不能以数字开头。可以使用单引号代替尖括号
 */

public class RegExp05 {
    public static void main(String[] args) {
        String content = "jiwenbo j77889900 w5544ji";
        /**
         * 非命名分组
         * 说明
         * 1、matcher.group(0)
         * 得到匹配的字符串
         * 2、matcher.group(1)
         * 得到匹配的字符串的第一个分组内容
         * 3、matcher.group(2)
         * 得到匹配的字符串的第二个分组内容
         */
        String regStr = "(\\d\\d)(\\d\\d)";//匹配四个数字
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到全部内容：" + matcher.group(0));
            System.out.println("找到第一组内容：" + matcher.group(1));
            System.out.println("找到第二组内容：" + matcher.group(2));
        }

        //命名分组
        String regStr1 = "(?<g1>\\d\\d)(?<g2>\\d\\d)";//匹配四个数字
        Pattern pattern1 = Pattern.compile(regStr1);
        Matcher matcher1 = pattern1.matcher(content);
        while (matcher1.find()) {
            System.out.println("找到全部：" + matcher1.group(0));
            System.out.println("找到g1：" + matcher1.group("g1"));
            System.out.println("找到g2：" + matcher1.group("g2"));
        }
    }
}
