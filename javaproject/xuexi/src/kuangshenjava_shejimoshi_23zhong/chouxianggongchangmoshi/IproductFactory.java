package kuangshenjava_shejimoshi_23zhong.chouxianggongchangmoshi;

//抽象产品工厂
public interface IproductFactory {
    //生成手机
    Iphone iphone();
    //生产路游戏
    Irouter irouter();
}
