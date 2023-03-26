package kuangshenjava_shejimoshi_23zhong.jingtaidailimoshi;

public class Proxy implements Rent {
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        host.rent();
        //看房
        seeHouse();
        //签合同
        hetong();
        //收中介费
        fare();
    }

    //看房
    public void seeHouse() {
        System.out.println("中介带你看房");
    }

    //签合同
    public void hetong() {
        System.out.println("签租赁合同");
    }

    //收中介费
    public void fare() {
        System.out.println("收中介费");
    }
}
