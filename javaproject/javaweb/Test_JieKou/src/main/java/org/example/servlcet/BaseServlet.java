package org.example.servlcet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    //根据请求最后一段路径来进行分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String uri = req.getRequestURI();
        //获取最后一段路径
        int index = uri.lastIndexOf('/');
        String s = uri.substring(index + 1);
        //获取字节码对象
        Class<? extends BaseServlet> aClass = this.getClass();
        //获取Method对象
        Method method = null;
        try {
            method = aClass.getMethod(s, HttpServletRequest.class, HttpServletResponse.class);
//            method = aClass.getDeclaredMethod(s, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}