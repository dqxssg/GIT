package kuangshenjava;

public class zengqiangforxunhuan {
    public static void main(String[] args) {
        //定义一个数组
        int[] numbers={10,20,30,40,50};


        for (int i = 0; i < 5; i++) {
            System.out.println(numbers[i]);
        }

        System.out.println("================================");

        //遍历数组的元素
        for (int x:numbers){
            System.out.println(x);
        }
    }
}
