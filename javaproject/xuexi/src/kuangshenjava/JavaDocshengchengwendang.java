package kuangshenjava;

public class JavaDocshengchengwendang {

    String name;


    /**
     * @author   作者名
     * @version   版本号
     * @since   指明需要最早使用的jdk版本
     * @param   参数名
     * @return   返回值情况
     * @throws   异常抛出情况
     */
    public String text(String name)throws Exception{
        return name;
    }

    //通过命令行：javadoc   参数   Java文件
    //通过Idea生成Javadoc文档
    //在Idea中选择工具（Tool）选项卡打开并选择Generate JavaDoc（生成javaDoc）
}

