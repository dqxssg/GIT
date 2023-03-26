package kuangshenjava;

public class BuHuoHePaoChuYiChang {
    /**
     * 异常处理机制
     *  抛出异常
     *  捕获异常
     * 异常处理五个关键字
     *  try（监控区域）、catch（捕获异常）、finally（处理善后工作）、throw（抛出异常）（放在程序之中）、throws
     */
    public static void main(String[] args) {
        int a=1;
        int b=0;
        try {//监控区域
            System.out.println(a/b);
        }catch (ArithmeticException arithmeticException){//捕获异常
            System.out.println("程序出现异常，变量b不能为0");
            arithmeticException.printStackTrace();//打印错误的信息
        }finally {//处理善后工作
            System.out.println("finally");
        }
        //finally可以不需要finally，假设IO，资源，关闭



        try {
            new BuHuoHePaoChuYiChang().test(1,0);
        }catch (ArithmeticException arithmeticException){
            arithmeticException.printStackTrace();
        }


    }

    public void test(int c,int d)throws ArithmeticException {
        if (d==0){
            throw new ArithmeticException();//主动抛出异常，一般在方法中使用
        }
    }
}
