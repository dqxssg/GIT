package kuangshenjava;

public class fangfadedingyihediaoyong {
    /**
     * 方法包含一个方法头和一个方法体
     *
     * 修饰符：修饰符，这是可选的，告诉编译器如何调用该方法，定义了该方法的返回类型
     * 返回值类型；方法可能返回值，returnValueType是方法返回值的数据类型，有些方法执行需要所需的操作，但没有返回值，在这种情况下，returnValueType是关键字void
     * 方法名；是方法的实际名称，方法名和参数表共同构成方法签名
     * 参数类型；参数像一个占位符，当方法被调用时，传递值给参数，这个值被称为实参或变量，参数列表是指方法的参数类型、顺序、参数的个数，参数是可选的，方法可以不包含任何参数
     * 形式参数；在方法被调用时用于接收外界输入的数据
     * 实参；调用方法时实际传给方法的数据
     * 方法体；方法体包含具体的语句，定义该方法的功能
     *
     */

    /**
     *
     * 调用方法：对象名.方法名（实参列表）
     * 当有返回一个值的时候，方法调用通常被当作一个值
     * 列：int larger = max（30，40）；
     * 如果方法返回值时void，方法调用一定是一条语句
     * 列：System.out.println（“Hello，lll”）
     *
     */

    //实参
    public static void main(String[] args) {
        int sum=add(1,2);
        System.out.println(sum);
        int bidaxiao=bidaxiao(2,5);
        System.out.println(bidaxiao);
    }


    //形式参数
    public static int add(int a,int b){
        return a+b;
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
}
