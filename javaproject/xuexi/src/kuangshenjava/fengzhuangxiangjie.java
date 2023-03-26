package kuangshenjava;

public class fengzhuangxiangjie {
    /**
     * 封装的详解
     *
     * 封装（数据的隐藏）通常，应禁止直接访问一个对象中的数据的实际表示，二应通过操作接口来访问，这称为信息隐藏
     *
     * 提高程序的安全性，保护数据
     * 隐藏代码的实现细节
     * 统一接口
     * 系统可维护增加
     */
    public static void main(String[] args) {
        fengzhuangxiangjie1 s1=new fengzhuangxiangjie1();
        s1.setName("lll");
        s1.setAge(18);
        System.out.println(s1.getName());
        System.out.println(s1.getAge());
    }
}
