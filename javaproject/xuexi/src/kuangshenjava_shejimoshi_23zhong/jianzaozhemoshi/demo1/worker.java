package kuangshenjava_shejimoshi_23zhong.jianzaozhemoshi.demo1;

//具体的建造者：工人
public class worker extends builder {


    private kuangshenjava_shejimoshi_23zhong.jianzaozhemoshi.demo1.product product;

    public worker() {
        product=new product();
    }

    @Override
    void builderA() {
        product.setBuilderA("地基");
        System.out.println("地基");
    }

    @Override
    void builderB() {
        product.setBuilderA("钢筋工程");
        System.out.println("钢筋工程");
    }

    @Override
    void builderC() {
        product.setBuilderA("铺电线");
        System.out.println("铺电线");
    }

    @Override
    void builderD() {
        product.setBuilderA("粉刷");
        System.out.println("粉刷");
    }

    @Override
    product getProDuct() {
        return product;
    }
}
