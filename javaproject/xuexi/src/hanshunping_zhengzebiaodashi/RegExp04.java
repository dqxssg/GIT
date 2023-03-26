package hanshunping_zhengzebiaodashi;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 元字符-定位符
 * 规定要匹配的字符串出现的位置，比如在字符串的开始还是在结束的位置
 * <p>
 * ^：指定起始字符
 * eg：^[0-9]+[a-z]*
 * 表示以至少1个数字开头，后接任意个小写字母的字符串。实列：123、6aa、555edf
 * <p>
 * $：指定结束字符
 * eg：^[0-9]\\-[a-z]+$
 * 表示以至少1个数字后接连字符“-”，并以至少1个小写字母结尾的字符串/实列：1-a
 * <p>
 * \\b：匹配目标字符串的边界
 * eg：han\\b
 * 表示这里说的字符串的边界指的是字串间有空格，或者字符串的结束位置。实列：hanshunpingsphannnhan
 * <p>
 * \\B：匹配目标字符串的的非边界
 * eg：han\\B
 * 和\b的含义刚刚相反。实列：hanshunpingsphannnhan
 */

public class RegExp04 {
    public static void main(String[] args) {

        String[] content = {"123abc", "abc123", "456abc", "abc456", "789abc", "abc789", "123 abc ", "456 abc ", "789 abc "};
        //^
        String regStr = "^[0-9]+[a-z]*";
        Pattern pattern = Pattern.compile(regStr);
        for (int i = 0; i < content.length; i++) {
            Matcher matcher = pattern.matcher(content[i]);
            while (matcher.find()) {
                System.out.println("找到^[0-9]+[a-z]*：" + matcher.group());
            }
        }
        //$
        String regStr1 = "^[a-z]+[0-9]+$";
        Pattern pattern1 = Pattern.compile(regStr1);
        for (int i = 0; i < content.length; i++) {
            Matcher matcher1 = pattern1.matcher(content[i]);
            while (matcher1.find()) {
                System.out.println("找到^[a-z]+[0-9]+$：" + matcher1.group());
            }
        }
        //\\b
        String regStr2 = "123abc\\b";
        Pattern pattern2 = Pattern.compile(regStr2);
        for (int i = 0; i < content.length; i++) {
            Matcher matcher2 = pattern2.matcher(content[i]);
            while (matcher2.find()) {
                System.out.println("找到123abc\\b：" + matcher2.group(0));
            }
        }
        //\\B
        String regStr3 = "abc\\B";
        Pattern pattern3 = Pattern.compile(regStr3);
        for (int i = 0; i < content.length; i++) {
            Matcher matcher3 = pattern3.matcher(content[i]);
            while (matcher3.find()) {
                System.out.println("找到abc\\B：" + matcher3.group());
            }
        }
    }

}
