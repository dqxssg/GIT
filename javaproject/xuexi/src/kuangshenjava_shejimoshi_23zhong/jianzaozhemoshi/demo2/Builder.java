package kuangshenjava_shejimoshi_23zhong.jianzaozhemoshi.demo2;

//建造者
public abstract class Builder {
    //汉堡
    abstract Builder builderA(String msg);

    //可乐
    abstract Builder builderB(String msg);

    //薯条
    abstract Builder builderC(String msg);

    //甜点
    abstract Builder builderD(String msg);

    abstract Product getProduct();
}
