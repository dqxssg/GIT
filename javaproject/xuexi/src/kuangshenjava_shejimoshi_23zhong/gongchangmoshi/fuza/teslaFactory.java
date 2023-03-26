package kuangshenjava_shejimoshi_23zhong.gongchangmoshi.fuza;

import kuangshenjava_shejimoshi_23zhong.gongchangmoshi.fuza.Car;

public class teslaFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new tesla();
    }
}
