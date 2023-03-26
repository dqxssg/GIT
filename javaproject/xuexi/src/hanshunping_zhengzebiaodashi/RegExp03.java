package hanshunping_zhengzebiaodashi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 元字符-限定符
 * 用于指定其前面的字符和组合项连续出现多少次+
 * <p>
 * *：指定字符重复0次或n次（无要求）
 * eg：（abc）*
 * 表示仅包含任意个abc的字符串，等效于\w*。实列：按不出，abcabcabc
 * <p>
 * +：指定字符重复一次或n次（至少一次）
 * eg：m+(abc)*
 * 表示以至少一个m开头，后接任意个abc的字符串。实列：m、mabc、mabcabc
 * <p>
 * ？：指定字符重复零次或一次（最到一次）
 * eg：m+abc？
 * 表示以至少一个m开头，后接ab或者abc的字符串。实列：mab、mabc、mmmab、mmabc
 * <p>
 * {n}：只能输入n个字符
 * eg：[abcd]{3}
 * 表示由abcd中的字母组成的任意长度为三的字符串。实列：abc、dbc、adc
 * <p>
 * {n,}：指定至少n个匹配
 * eg：[abcd]{3,}
 * 表示由abcd中的字母组成的任意长度不小于3的字符串。实列：aab、dbc、aaabdc
 * <p>
 * {n,m}：指定至少n个但不多于m个匹配(贪婪匹配先匹配最多的)
 * eg：[abcd]{3,5}
 * 表示由abcd中字母组成的任意长度不小于三，不大于五的字符串。实列：abc、abcd、aaaaa、bcdab
 */
public class RegExp03 {
    public static void main(String[] args) {
        String contet = "112233aabbcc1112233aabbcc";
        //{n}
        String regStr = "1{2}";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(contet);
        while (matcher.find()) {
            System.out.println("找到1{2}：" + matcher.group(0));
        }
        //{n,m}
        String regStr1 = "a{1,2}";
        Pattern pattern1 = Pattern.compile(regStr1);
        Matcher matcher1 = pattern1.matcher(contet);
        while (matcher1.find()) {
            System.out.println("找到a{1,2}：" + matcher1.group(0));
        }
    }
}
