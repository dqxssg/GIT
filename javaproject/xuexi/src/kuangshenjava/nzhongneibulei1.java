package kuangshenjava;

public class nzhongneibulei1 {
    private int id=10;
    public void out(){
        System.out.println("这是外部类的方法");
    }
    class Inner{
        public void in(){
            System.out.println("这是内部类的方法");
        }
        //获得外部类的私有属性
        public void getID(){
            System.out.println(id);
        }
    }
    //局部内部类
    public void method(){
        class Inner{
            public void in(){

            }
        }
    }
}
