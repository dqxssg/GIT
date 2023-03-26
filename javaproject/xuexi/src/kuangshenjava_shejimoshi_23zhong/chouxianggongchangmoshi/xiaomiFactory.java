package kuangshenjava_shejimoshi_23zhong.chouxianggongchangmoshi;

public class xiaomiFactory implements IproductFactory {
    @Override
    public Iphone iphone() {
        return new xiaomiIphone();
    }

    @Override
    public Irouter irouter() {
        return new xiaomiIrouter();
    }
}
