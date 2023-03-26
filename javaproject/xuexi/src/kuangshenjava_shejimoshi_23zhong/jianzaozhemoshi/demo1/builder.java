package kuangshenjava_shejimoshi_23zhong.jianzaozhemoshi.demo1;

//抽象的建造者：方法
public abstract class builder {
    //地基
    abstract  void builderA();
    //钢筋工程
    abstract  void builderB();
    //铺电线
    abstract  void builderC();
    //粉刷
    abstract  void builderD();
    //完工：得到产品
    abstract product getProDuct();
}
