package kuangshenjava;

public class DoWhilexunhuan {
    //先循环再执行
    public static void main(String[] args) {
        int i=0;
        int sum=0;
        do {
            sum=sum+i;
            i++;
        }while (i<=100);
        System.out.println(sum);
        System.out.println("===========================");
        int a=0;
        do {
            System.out.println(a);
            a++;
        }while (a<0);
    }
}
