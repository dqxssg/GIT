package kuangshenjava_shejimoshi_23zhong.gongchangmoshi.jiandan;

//静态工厂模式
//增加一个新的产品，如果你不修改代码，做不到
//开闭原则
public class CarFactory {
    //方法一：
    public static Car getcar(String car) {
        if (car.equals("五菱")) {
            return new wuling();
        } else if (car.equals("特斯拉")) {
            return new tesla();
        } else {
            return null;
        }
    }

    //方法二：
    public static Car getwuling(){
        return new wuling();
    }

    public static Car gettesila(){
        return new tesla();
    }
}
