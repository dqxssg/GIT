package kuangshenjava_shejimoshi_23zhong.gongchangmoshi.fuza;

import kuangshenjava_shejimoshi_23zhong.gongchangmoshi.fuza.Car;
import kuangshenjava_shejimoshi_23zhong.gongchangmoshi.fuza.wuling;

public class wulingFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new wuling();
    }
}
