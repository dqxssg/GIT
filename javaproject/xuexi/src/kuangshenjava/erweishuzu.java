package kuangshenjava;

public class erweishuzu {
    /**
     * 多维数组
     * 1、所谓数组可以看成是数组的数组，比如二维数组就是一个特殊的一维数组，其每一个元素都是一个一维数组
     * 2、二维数组：
     * int a[][]=new int[2][5];
     * 3、解析：以上二维数组a可以看成一个两行五列的数组
     * 4、思考：多维数组的使用
     */
    public static void main(String[] args) {
        int[][] array={{1,2},{2,3},{3,4}};

        System.out.println(array[2][0]);
        System.out.println("=================");
        System.out.println(array[1][1]);
        System.out.println("=================");
        System.out.println(array[0][0]);
        System.out.println("=================");
        System.out.println(array[2][1]);
        System.out.println("=================");
        System.out.println(array[1][0]);
        System.out.println("=================");
        printArray(array[0]);
        System.out.println("=================");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.println(array[i][j]);
            }
        }
    }
    //打印数组
    public static void printArray(int[] arrays){
        for (int i = 0; i < arrays.length; i++) {
            System.out.println(arrays[i]);
        }
    }
}
