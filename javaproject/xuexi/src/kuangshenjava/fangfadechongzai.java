package kuangshenjava;

public class fangfadechongzai {
    /**
     *
     *重载就是在一个类中，有相同的函数名称，但形参不同的函数
     * 方法的重载的规则
     * 1、方法名称必须相同
     * 2、参数列表必须不同（个数不同、或类型不同、参数排列顺序不同等）
     * 3、方法的返回类型可以形同也可以不相同
     * 4、仅仅返回类型不同不足以成为方法的重载
     * 事先理论：方法名称相同时，编译器会根据调用方法的的参数个数、参数类型等去逐个匹配，以选择对应的方法，如果匹配失败，则编译器报错
     *
     */
    public static void main(String[] args) {
        int bidaxiao=bidaxiao(2,5);
        System.out.println(bidaxiao);
    }
    public static int bidaxiao(int c,int d){
        int result=0;
        if (c==d){
            System.out.println("相等");
            return 0;//终止方法
        } else if (c > d) {
            result=c;
        }else {
            result=d;
        }
        return result;//终止方法
    }
    public static double bidaxiao(double c,double d){
        double result=0;
        if (c==d){
            System.out.println("相等");
            return 0;//终止方法
        } else if (c > d) {
            result=c;
        }else {
            result=d;
        }
        return result;//终止方法
    }
}
