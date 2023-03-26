package kuangshenjava;

public class zidingyiyicahngjijingyanxiaojie {
    /**
     * 自定义异常
     *
     * 需要继承Exception
     *
     *
     * 使用Java内置的异常类可以描述在编程时出现的大部分异常情况，除此之外，用户还可以自定义异常，用户自定义类，只能继承Exception类即可
     * 在程序中使用自定义异常类，大体可分为以下几个步骤
     * 1、创建自定义异常类
     * 2、在方法中通过throw关键字抛出异常对象
     * 3、如果在当前抛出异常的方法中处理异常，可以使用try-catch语句捕获并处理，否则在方法的声明处通过throws关键字指明要抛出给方法调用者的异常，继续进行下一步操作
     * 4、在出现异常方法的调用者中捕获并处理异常
     *
     *
     * 实际应用中的经验总结
     * 1、处理运行时异常时，采用逻辑去合理规避同时辅助try-catch处理
     * 2、在多重catch块后面，可以加一个catch(Exception)来处理可能会被遗漏的异常
     * 3、对于不确定的代码，也可以加上try-catch。处理潜在的异常
     * 4、尽量去处理异常，切记只是简单的调用printStackTrace()去打印输出
     * 5、具体如何处理异常，要根据不同的业务需求和异常去决定
     * 6、尽量添加finally语句块去释放占用的资源
     */
}