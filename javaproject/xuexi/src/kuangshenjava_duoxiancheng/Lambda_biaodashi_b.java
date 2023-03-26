package kuangshenjava_duoxiancheng;

/**
 * 总结
 * Lamda表达式只有一行代码的情况下才能简化为一行，如果多行，那么就用代码块包裹
 * 前提是接口为函数式接口
 * 多个参数也可以去不掉参数类型，要去掉就都去掉
 */

public class Lambda_biaodashi_b {

    public static void main(String[] args) {

        //和Lambda表达式不可同时用
        //Ilove ilove=null;

        //Lambda表达简化
        Ilove ilove = (int a) -> {
            System.out.println("I LOVE YOU-->" + a);
        };


        //去掉类型，简化
        ilove=(int a) -> {
            System.out.println("I LOVE YOU-->" + a);
        };

        //去掉括号，简化
        ilove=a->{
            System.out.println("I LOVE YOU-->" + a);
        };

        //去掉大括号，简化
        ilove=a->System.out.println("I LOVE YOU-->" + a);


        ilove.love(521);
    }
}

interface Ilove {
    void love(int a);
}
