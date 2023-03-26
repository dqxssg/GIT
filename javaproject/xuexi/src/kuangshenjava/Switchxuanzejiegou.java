package kuangshenjava;

//switch语句中的变量类型可以是
//1、byte、short、int、char、String
//2、同时case标签必须是字符串常量或者字面量

public class Switchxuanzejiegou {
    public static void main(String[] args) {
        char grade='C';
        switch (grade){
            case 'A':
                System.out.println(1);
                break;
            case 'B':
                System.out.println(2);
                break;
            case 'C':
                System.out.println(3);
                break;
            case 'D':
                System.out.println(4);
                break;
            default:
                System.out.println(5);
                break;
        }
    }
}
