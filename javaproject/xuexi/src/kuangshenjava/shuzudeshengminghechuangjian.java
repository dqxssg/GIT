package kuangshenjava;

public class shuzudeshengminghechuangjian {

    /**
     * 数组的声明创建
     * 首先必须声明数组变量，才能在程序中使用数组，下面是声明数组变量的语法：
     * dataType[] arrayRefVar;首选的方法
     * 或
     * dataType arrayRefVar[];效果相同，但不是首选方法
     *
     * java语言使用new操作符来创建数组，语法如下：
     * dataType[] arrayRefVar = new dataType[arraysize];
     *
     * 数组的元素是通过索引访问的，数组索引从0开始
     *
     * 获取数组长度：
     * arrays.length
     */



    //变量的类型 变量的名字 = 变量的值;
    //数组的类型
    public static void main(String[] args) {
        //声明一个数组
        int[] number;
        //这里可以存放10个int类型的数字
        number=new int[10];
        //给数组元素中赋值
        number[0]=0;
        number[1]=1;
        number[2]=2;
        number[3]=3;
        number[4]=4;
        number[5]=5;
        number[6]=6;
        number[7]=7;
        number[8]=8;
        number[9]=9;
        //计算所有元素的和
        int sum=0;
        for (int i = 0; i < number.length; i++) {
            sum=sum+number[i];
        }

        System.out.println(sum);
    }
}
