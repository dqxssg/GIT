package kuangshenjava;

public class xishushuzu {
    /**
     * 稀疏数组
     *
     * 当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
     *
     * 稀疏数组的处理方式：
     * 记录数组一共有几行几列，有多少个不同的值
     * 把具有不同值的元素和行列及值记录在一个小规模的数组中，从而缩小程序的规模
     *
     * 如下图：左边是原始数组，右边是稀疏数组
     * 0    0    0    22   0   0    15                          行（row）       列（col）       值（value）
     * 0    11   0    0    0   17   0                    [0]       6              7               8
     * 0    0    0    -6   0   0    0                    [1]       0              3               22
     * 0    0    0    0    0   39   0                    [2]       0              6               15
     * 91   0    0    0    0   0    0                    [3]       1              1               11
     * 0    0    28   0    0   0    0                    [4]       1              5               17
     *                                                   [5]       2              3               -6
     *                                                   [6]       3              5               39
     *                                                   [7]       4              0               91
     *                                                   [8]       5              2               28
     */
    public static void main(String[] args) {
        //创建一个二维数组   11*11   0代表没有棋子   1代表黑棋   2代表白棋
        int[][] array1=new int[11][11];
        array1[1][2]=1;
        array1[2][3]=2;
        //输出原始的数组
        System.out.println("输出原始的数组");
        System.out.println("===============================================");
        for (int[] ints:array1){
            for (int anInt:ints){
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        System.out.println("===============================================");
        //转换为稀疏数组保存
        //获取有效值的个数
        int sum=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (array1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println(sum);
        System.out.println("===============================================");
        //创建一个稀疏数组的数组
        int[][] array2=new int[sum+1][3];
        array2[0][0]=11;
        array2[0][1]=11;
        array2[0][2]=sum;
        //遍历二维数组，将非零的值，存放到稀疏数组中
        int count=0;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if (array1[i][j]!=0){
                    count++;
                    array2[count][0]=i;
                    array2[count][1]=j;
                    array2[count][2]=array1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组");
        for (int i = 0; i < array2.length; i++) {
            System.out.println(array2[i][0]+"\t"+array2[i][1]+"\t"+array2[i][2]+"\t");
        }
        System.out.println("===============================================");
        //还原稀疏数组
        System.out.println("还原稀疏数组");
        //读取稀疏数组的值
        int[][] array3=new int[array2[0][0]][array2[0][1]];
        //给其中的元素还原值
        for (int i = 1; i < array2.length; i++) {
            array3[array2[i][0]][array2[i][1]]=array2[i][2];
        }
        //打印
        System.out.println("输出还原的数组");
        for (int[] ints:array3){
            for (int anInt:ints){
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
    }
}
