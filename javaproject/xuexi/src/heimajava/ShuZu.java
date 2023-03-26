package heimajava;

import java.util.Random;

/**
 *1、格式一：数据类型[] 数组名
 *         int[] array;
 *2、格式二：数据类型 数组名[]
 *         int array[];
 *3、数组的初始化
 *       初始化：就是在内存中，为数组容器开辟空间，并将数据存入容器中的过程
 *       数组的初始化的两种方式：
 *          静态初始化
 *             完整格式：数据类型[] 数组名=new 数据类型[]{元素1,元素2,...};
 *                      int[] array=new arr[]{1,2,3};
 *             简化格式：数据类型[] 数组名={元素1,元素2,...};
 *                      int[] array={1,2,3};
 *          动态初始化：初始化时只指定指定数组长度，由系统为数组分配初始值
 *             格式：数据类型[] 数组名=new 数据类型[数组长度];
 *                  int[] arrat=new int[3]
 *      动态初始化和静态初始化的区别：
 *         动态初始化：手动指定数组长度，由系统给出默认初始化值
 *            只明确元素个数，不明确具体数值，推荐使用动态初始化
 *         静态初始化：手动指定数组元素，系统会根据元素个数，计算出数组长度
 *            需求中已明确了要操作的具体数据，直接静态初始化即可
 *4、数组元素访问
 *    格式：数组名[索引];（索引：也叫下标，角标）（索引的特点：从0开始，逐个+1增长，连续不断）
 *    利用索引对数组中的元素进行访问
 *       获取数组中的元素
 *          格式：数组名[索引];
 *       把数据储存到数组之中
 *          格式1：数组名[索引]=具体数据变量;
 *          格式2：数组名[索引]=变量;
 *          细节：一旦覆盖之后原来的数据就不存在了
 *5、数组的遍历：将数组中的所有的内容取出来，取出来以后可以（打印，求和，判断...）
 *   注意：遍历指的是取出数据的过程，不要局限地理解为，遍历就是打印
 *   利用的for循环高效代码，开始条件：0，结束条件（最大的索引）：数组的长度-1
 *   在JAVA当中，关于数组的长度的属性为length，调用方式：数值名.length
 *   拓展：自动的快速生成数组的遍历（idea提供）：数组名.fori
 *   数组的遍历中一个for循环尽量只做一件事情
 *6、数组常见问题
 *    当访问了数组中不存在的索引，就会引发索引越界异常
 *
 *
 *
 *
 *
 *
 *
 *
 *7、
 *8、
 *9、
 *10、
 *11、
 *12、
 *13、
 *14、
 *15、
 *16、
 *17、
 *18、
 *19、
 *20、
 */

public class ShuZu {
    public static void main(String[] args) {
//静态初始化
        //1、定义五个整数
        //[I@3b07d329(地址值)
        //[：表示当前是一个数组
        //I：表示当前数组里面的元素的类型，都是int类型的
        //@：表示间隔符号，固定格式
        //3b07d329：数组真正的地址值，十六进制
        int[] array1 = {1, 2, 3, 4, 5};
        //2、定义三个姓名
        //[Ljava.lang.String;@41629346(地址值)
        //[：表示当前是一个数组
        //@：表示间隔符号，固定格式
        //41629346：数组真正的地址值，十六进制
        String[] array2 = {"张三", "李四", "王五"};
        //3、定义四个小数
        //[D@404b9385(地址值)
        //[：表示当前是一个数组
        //D：表示当前数组里面的元素的类型，都是double类型的
        //@：表示间隔符号，固定格式
        //404b9385：数组真正的地址值，十六进制
        double[] array3 = {1.1, 2.2, 3.3, 4.4};
//动态初始化
        //定义一个数组，用来存班级中50个学生的姓名
        //性命未知，等学生报道之后，再进行添加
        //格式：数据类型[] 数组名=new 数据类型[数组长度];
        //在创建的时候，由我们自己指定数组长度，由虚拟机给出默认的初始化值
        String[] array4=new String[50];
        //添加学生
        array4[0]="张三";
        array4[1]="李四";
        array4[2]="王五";
        //获取
        System.out.println("array4[0]"+array4[0]);
        System.out.println("array4[1]"+array4[1]);
        System.out.println("array4[2]"+array4[2]);
        //打印出来默认初始化值
        /**
         * 数组默认初始化值
         * 整数类型；默认初始化值为0
         * 小数类型；默认初始化值为0.0
         * 字符类型；默认初始化值为'/u0000'空格
         * 布尔类型；默认初始化值为false
         * 引用数据类型；默认初始化值为null（八大数据类型，除了以上四种，默认初始化值都为null）
         */
        System.out.println("array4[3]"+array4[3]);
//利用索引对数组中的元素进行访问
        //获取数组中的元素，格式：数组名[索引];
        int a = array1[0];
        System.out.println("array1[0]="+a);
        String b = array2[0];
        System.out.println("array2[0]="+b);
        double c = array3[0];
        System.out.println("array3[0]="+c);
        //把数据储存到数组之中，格式：数组名[索引]=具体数据/变量;
        array1[1]=100;
        System.out.println("array1[1]赋值后="+array1[1]);
        array2[1]="100";
        System.out.println("array2[1]赋值后="+array2[1]);
        array3[1]=100;
        System.out.println("array3[1]赋值后="+array3[1]);
//数组的遍历
        //利用的for循环高效代码，开始条件：0，结束条件（最大的索引）：数组的长度-1
        //在JAVA当中，关于数组的长度的属性为length，调用方式：数值名.length
        //拓展：自动的快速生成数组的遍历（idea提供）：数组名.fori
        for (int i = 0; i < array1.length; i++) {
            System.out.println("遍历array1="+array1[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            System.out.println("遍历array2="+array2[i]);
        }
        for (int i = 0; i < array3.length; i++) {
            System.out.println("遍历array3="+array3[i]);
        }
        //数组的遍历课后小题1（定义一个数组，储存1，2，3，4，5，遍历数组得到每一个元素，求数组里面所有的数据和）
        int[] array5={1,2,3,4,5};
        int sum=0;
        for (int i = 0; i < array5.length; i++) {
            System.out.println("数组的遍历课后小题1，遍历的数组结果"+array5[i]);
            sum+=array5[i];
        }
        System.out.println("数组的遍历课后小题1，sum="+sum);
        //数组的遍历课后小题2（定义一个数组，储存1，2，3，4，5，6，7，8，9，10，遍历数组得到每一个元素，统计素组里面有多少个能被3整除的数字）
        int[] array6={1,2,3,4,5,6,7,8,9,10};
        int intsum=0;
        for (int i = 0; i < array6.length; i++) {
            System.out.println("数组的遍历课后小题2，遍历的数组结果："+array6[i]);
            if (array6[i]%3==0){
                System.out.println("数组的遍历课后小题2，能被3整除的数字："+array6[i]);
                intsum++;
            }
        }
        System.out.println("数组的遍历课后小题2，intsum"+intsum);
        //数组的遍历课后小题3（定义一个数组，储存1，2，3，4，5，6，7，8，9，10，遍历数组得到每一个元素，如果是奇数，则将当前数字扩大两倍；如果是偶数，则将当前数字变成二分之一）
        int[] array7={1,2,3,4,5,6,7,8,9,10};
        for (int i = 0; i < array7.length; i++) {
            System.out.println("数组的遍历课后小题3，遍历的数组结果："+array7[i]);
            if (array7[i]%2==0){
                array7[i]/=2;
                System.out.println("数组的遍历课后小题3，偶数变成当前数字的二分之一："+array7[i]);
            }else {
                array7[i]*=2;
                System.out.println("数组的遍历课后小题3，奇数变成当前数字的二倍："+array7[i]);
            }
        }
//数组常见问题
        //当访问了数组中不存在的索引，就会引发索引越界异常
        /**
         * 索引越界异常
         * 原因：访问了不存在的索引
         * 避免：索引的范围
         */
        int[] array8= {1,2,3,4,5};
        //长度：5
        //最小索引：0
        //最大索引：4（数组的长度-1）
        //System.out.println(array8[5]);//(索引错误)
//数组练习-求最值
        /**
         * 用来储存最大值的变量不能为0，必须是数组中第一个数
         * 循环的开始条件为0，为了提高效率不用自身和自身比较
         */
        //需求：已知数组元素{33，5，22，44，55}，找出最大值并打印下来
        //定义一个数组
        int[] array9={33,5,22,44,55};
        //max用来储存最大值
        int max=array9[0];
        for (int i = 0; i < array9.length; i++) {
            if (max>array9[i]){
                max=max;
            }else {
                max=array9[i];
            }
        }
        System.out.println("最大值:"+max);
//遍历数组求和
        /**
         * 要求：生成10到100之间的随机数组存入数组
         * 1、求出所有数据的和
         * 2、求出数据的平均数
         * 3、统计有多少个数据比平均数值小
         */
        int[] array10=new int[10];
        //生成随机数
        Random random=new Random();
        //循环生成随机数并将随机数放到数组array[10]中
        for (int i = 0; i < array10.length; i++) {
            //每次循环，生成一个新的随机数
            int numeber = random.nextInt(100) + 1;
            array10[i]=numeber;
        }
        int he=0;
        int pingjunshu=0;
        int bipingjunshuxiaodeshudeshuliang=0;
        //遍历数组求和
        for (int i = 0; i < array10.length; i++) {
            he+=array10[i];
        }
        //求平均数
        pingjunshu=he/array10.length;
        //遍历数组求比平均数小的数的个数
        for (int i = 0; i < array10.length; i++) {
            //判断和平均数谁小
            if (array10[i]<pingjunshu){
                bipingjunshuxiaodeshudeshuliang++;
            }
        }
        System.out.println("和:"+he);
        System.out.println("平均数:"+pingjunshu);
        System.out.println("比平均数值小的个数:"+bipingjunshuxiaodeshudeshuliang);
//交换数组中的数据
        /**
         * 要求：定义一个数组，存入1，2，3，4，5。按照要求交换索引对应的元素
         * 交换前：1，2，3，4，5
         * 交换后：5，4，3，2，1
         */
        //第一种方法
        int[] array11= {1,2,3,4,5};
        int[] array12= {5,4,3,2,1};
        int[] array13= new int[5];
        for (int i = 0; i < array11.length; i++) {
            array11[i]=array12[i];
        }
        for (int i = 0; i < array11.length; i++) {
            System.out.println(array11[i]+" ");
        }
    }
}
