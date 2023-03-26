package kuangshenjava_shejimoshi_23zhong.chouxianggongchangmoshi;

public class huaweiFactory implements IproductFactory {
    @Override
    public Iphone iphone() {
        return new huaweiIphone();
    }

    @Override
    public Irouter irouter() {
        return new huaweiIrouter();
    }
}
