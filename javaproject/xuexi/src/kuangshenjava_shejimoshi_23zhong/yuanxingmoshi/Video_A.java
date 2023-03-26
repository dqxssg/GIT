package kuangshenjava_shejimoshi_23zhong.yuanxingmoshi;

import java.util.Date;

public class Video_A implements Cloneable{
    private Date Time;
    private String  name;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object = super.clone();

        //实现深克隆
        Video_A videoA = (Video_A) object;
        //将这个对象的属性也进行克隆
        videoA.Time = (Date) this.Time.clone();

        return object;
    }

    public Video_A(Date time, String name) {
        Time = time;
        this.name = name;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Video_A{" +
                "Time=" + Time +
                ", name='" + name + '\'' +
                '}';
    }
}
