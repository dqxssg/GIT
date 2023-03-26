package org.example.response;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字节数据：设置字节数据的响应体
 */
@WebServlet("/servlet4")
public class servlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、读取文件
        FileInputStream fileInputStream = new FileInputStream("d:\\照片/QQ.png");
        //2、获取response字节输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //3、完成流的copy
//        byte[] buff=new byte[1024];
//        int len=0;
//        while ((len=fileInputStream.read(buff))!=-1){
//            outputStream.write(buff,0,len);
//        }
        //第三步可以换成下面，在导个包
//        <dependency>
//            <groupId>commons-io</groupId>
//            <artifactId>commons-io</artifactId>
//            <version>2.6</version>
//        </dependency>
        IOUtils.copy(fileInputStream, outputStream);

        fileInputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
