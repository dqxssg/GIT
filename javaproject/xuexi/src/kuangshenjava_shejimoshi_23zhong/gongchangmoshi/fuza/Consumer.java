package kuangshenjava_shejimoshi_23zhong.gongchangmoshi.fuza;

import kuangshenjava_shejimoshi_23zhong.gongchangmoshi.fuza.Car;

public class Consumer {
    public static void main(String[] args) {
        Car wuling = new wulingFactory().getCar();
        Car tesla = new teslaFactory().getCar();

        wuling.name();
        tesla.name();
    }
}
