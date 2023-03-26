package kuangshenjava_shejimoshi_23zhong.qiaojiemoshi;

//抽象的电脑类型类
public abstract class Computer {
    //组合、品牌
    protected Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void info(){
        //自带品牌
        brand.info();
    }
}
//台式机
class Desktop extends Computer {
    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("台式机");
    }
}
//笔记本
class Laptop extends Computer {
    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("笔记本");
    }
}

