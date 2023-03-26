package kuangshenjava;

public class Break_Continue_Goto {

    /**
     * break在任何循环语句中的主体部分均可以用break控制循环的流程
     * break用于强行退出循环，不执行循环语句中的剩余语句（break语句也可再swith语句中使用）
     * continue语句用在循环语句中，用于终止某次循环过程，即跳过循环体中尚未执行的语句，接着进行下一次是否执行循环的判断
     */

    public static void main(String[] args) {
        int i=0;
        while (i<100){
            i++;
            System.out.println(i);
            if (i==30){
                break;
            }
        }
        System.out.println("================================");
        int j=0;
        while (j<100){
            j++;
            if (j%10==0){
                System.out.println();
                continue;
            }
            System.out.println(j);
        }
        System.out.println("================================");
        int count=0;
        outer:for (int k=101;k<150;k++) {
            int m;
            for (m = 2; m < k / 2; m++) {
                if (m % k == 0) {
                    continue outer;
                }
            }
            System.out.println(m+" ");
        }
    }
}
