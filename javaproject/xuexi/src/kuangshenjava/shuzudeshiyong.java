package kuangshenjava;

public class shuzudeshiyong {
    /**
     * 数组deshiyong
     * 1、For-Each循环
     * 2、数组做方法入参
     * 3、数组作返回值
     */
    public static void main(String[] args) {
        int[] arrays = {1,2,3,};
        //打印全部的数组元素
        for (int i = 0; i < arrays.length; i++) {
            System.out.println(arrays[i]);
        }
        System.out.println("===================");
        int sum = 0;
        for (int i = 0; i < arrays.length; i++) {
            sum+=arrays[i];
        }
        System.out.println(sum);
        System.out.println("===================");
        //查找最大元素
        int max=arrays[0];
        for (int j = 1; j < arrays.length; j++) {
            if (max<arrays[j]){
                max=arrays[j];
            }
        }
        System.out.println(max);
        System.out.println("===================");
        int[] arrayss={1,2,3,4,5,6};
        printArryar(arrayss);
        printArryar(reverse(arrayss));
    }
    //打印数组
    public static void printArryar(int[] arrayss){
        for (int i = 0; i < arrayss.length; i++) {
            System.out.print(arrayss[i]+" ");
        }
    }
    //反转数组
    public static int[] reverse(int[] arrayss){
        int[] result=new int[arrayss.length];
        //反转的操作
        for (int i = 0,j=result.length-1; i < arrayss.length; i++,j--) {
            result[j]=arrayss[i];
        }
        return result;
    }
}
