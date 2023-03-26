package kuangshenjava;

public class ErroeheException {
    /**
     * 异常机制
     *
     * Error
     * Error类对象有Java虚拟机生成并抛出，大多数错误与代码编写者所执行的操作无关
     * Java虚拟机运行错误（VirtualMachineError）当JVM不再继续执行操作所需要的内存资源是，将出现OutOfMemoryError，这些异常发生时，Java虚拟机（JVM）一般会选择线程终止
     * 还有发生在虚拟机试图执行应用时，如定义错误（NoClassDefFoundError）、链接错误（LinkageError），这些错误是不可查的，因为他们在程序的控制和处理能力之外，而且绝大多数是程序运行时不允许出现的状况
     *
     * Exception
     * 在Exception分支中有一个重要的子类RuntimeException（运行异常）
     * ArraylndexOutOfBoundsException（数组下标越界）
     * NullPointerException（空指针异常）
     * ArithmeticException（算数异常）
     * MissingResourceException（丢失异常）
     * ClassNotFoundException（找不到类）等异常，这些异常是不检查异常，程序中可以选则捕获处理，也可以不处理
     * 这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生
     * Error和Exception的区别：Error通常是灾难性的致命的错误，是程序无法控制和处理的，当出现这些异常时，Java虚拟机（JVM）一般会选择终止线程；Exception通常情况下是可以被程序处理的，并且在程序中应该尽可能的去处理这些异常
     *
     */
}
