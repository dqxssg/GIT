package kuangshenjava_shejimoshi_23zhong.jianzaozhemoshi.demo1;

public class text {
    public static void main(String[] args) {
        //指挥
        director dir = new director();
        //指挥，具体的工人完成产品
        product build = dir.build(new worker());
        System.out.println(build.toString());
    }
}
