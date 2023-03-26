package kuangshenjava_shejimoshi_23zhong.gongchangmoshi.jiandan;

public class Consumer {
    public static void main(String[] args) {

        //使用工厂创建

        //方法一
        Car car = CarFactory.getcar("五菱");
        Car car1 = CarFactory.getcar("特斯拉");
        Car car2 = CarFactory.getcar("比亚迪");//null
        Car car3 = CarFactory.getcar(" ");//null
        car.name();
        car1.name();
        car2.name();
        car3.name();
        //方法二
        Car car4 = CarFactory.gettesila();
        Car car5 = CarFactory.getwuling();
        car4.name();
        car5.name();
    }
}
