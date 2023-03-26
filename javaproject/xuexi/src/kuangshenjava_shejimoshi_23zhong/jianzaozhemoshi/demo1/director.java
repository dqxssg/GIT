package kuangshenjava_shejimoshi_23zhong.jianzaozhemoshi.demo1;

//指挥：核心，负责指挥构建一个工程，工程如何构建，由它决定
public class director {
    //指挥工人按照顺序建房子
    public static product build(builder builder){
        builder.builderA();
        builder.builderB();
        builder.builderC();
        builder.builderD();

        return builder.getProDuct();
    }

}
