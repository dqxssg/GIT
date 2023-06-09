package kuangshenjava_duoxiancheng;

public class xiancheng_jincheng_duoxaincheng {
    /**
     * 线程、进程、多线程
     *
     * Process（进程）和Thread（线程）
     *
     * 说起进程，就不得不说下程序，程序是指令和数据的有序集合，其本身没有任何运行的含义，是一个静态的概念
     * 而进程则是执行程序的一次执行过程，它是一个动态的概念，是系统资源分配的单位
     * 通常在一个进程中可以包含若干个线程，当然一个进程中至少有一个线程，不然没有存在的意义，线程是cpu调度和执行的单位
     * 注意：很多多线程是模拟出来的，真正的多线程是指有多个cpu，即多核，如服务器，如果是模拟出来的多线程，即在一个cpu的情况下，在同一个时间点，cpu只能执行一个代码，因为切换的很快，所以就有同时执行的错误
     */

    /**
     * 核心
     * 线程就是独立的执行路径
     * 在程序运行时，即使没有自己创建的线程2，后台也会有多个线程，如：主线程序、gc程序
     * main()称之为主线程，为系统的入口，用于执行整个程序
     * 在一个进程中，如果开辟了多个程序，线程的运行有调度器安排调度，调度器是与操作系统紧密相关的，先后顺序是不能认为的干预
     * 对同一份资源操作时，会存在资源掠夺的问题，需要加入并发控制
     * 线程会带来额外的开销，如cpu调度时间，并发控制开销
     * 每个线程在自己的工作内存交互，内存控制不当会造成数据不一致
     */
}
