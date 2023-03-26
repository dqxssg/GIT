package kuangshenjava;

public class sanyuanyunsuanfujixiaojie1 {
    //三元运算符   ?   :
    public static void main(String[] args) {

        //x ? y : z
        // 如果x==true，则结果为y否则为z

        int score=80;
        String type=score<60?"不及格":"及格";//必须掌握
        System.out.println(type);
    }
}
