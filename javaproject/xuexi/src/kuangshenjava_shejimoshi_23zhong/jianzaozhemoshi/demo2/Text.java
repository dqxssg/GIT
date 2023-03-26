package kuangshenjava_shejimoshi_23zhong.jianzaozhemoshi.demo2;

public class Text {
    public static void main(String[] args) {
        //服务员
        Worker worker=new Worker();
        //链式编程，在原类的基础上，可以自由组合，如果不组合，也有默认的
        Product product= worker
                .builderA("全家桶")
                .builderB("雪碧")
                .getProduct();
        System.out.println(product.toString());
    }
}
