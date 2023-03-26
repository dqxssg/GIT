package kuangshenjava_duoxiancheng;

/**
 * 推到Lamda表达式
 */
public class Lambda_biaodashi_a {

    //静态内部类
    //实现类
    static class Like1 implements ILike {

        @Override
        public void lambda() {
            System.out.println("I LIKE Lambda1");
        }
    }

    public static void main(String[] args) {

        ILike like = new Like();
        like.lambda();

        like = new Like1();
        like.lambda();

        //局部内部类
        //实现类
        class Like2 implements ILike {

            @Override
            public void lambda() {
                System.out.println("I LIKE Lambda2");
            }
        }
        like = new Like2();
        like.lambda();

        //匿名内部类,没有类的名称，必须借助接口或者父类
        //实现类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I LIKE Lambda3");
            }
        };
        like.lambda();

        //用Lambda简化
        //实现类
        like = () -> {
            System.out.println("I LIKE Lamda4");
        };
        like.lambda();
    }

}

//定义一个函数式接口
interface ILike {
    void lambda();
}

//实现类
class Like implements ILike {

    @Override
    public void lambda() {
        System.out.println("I LIKE Lambda");
    }
}

