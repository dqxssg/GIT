package hanshunping_zhengzebiaodashi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 元字符-选择匹配符
 * 在匹配某个字符串的时候是选择性的，即可以选择这个又可以选择那个，这个时候就用选择匹配符
 * |：匹配 | 之前或之后的表达式
 * eg：ab|cd表示ab或则cd
 */
public class RegExp02 {
    public static void main(String[] args) {
        String content = "jiwenbo      JIWENBO      姬文博";
        String regStr = "ji|JI|姬";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("ji|JI|姬：" + matcher.group(0));
        }
    }
}
