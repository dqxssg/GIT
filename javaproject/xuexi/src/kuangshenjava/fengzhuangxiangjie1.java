package kuangshenjava;

public class fengzhuangxiangjie1 {

    //性命
    private String name;
    //年龄
    private int age;

    //提供一些可以操作这个属性的方法
    //提供一些public的get、set方法

    //get获取这个数据
    public String getName(){
        return this.name;
    }
    //set给这个数设置值
    public void setName(String name){
        this.name=name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age>120||age<0){
        this.age = 3;
    }else {
            this.age=age;
        }
    }
}
