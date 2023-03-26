package com.example.boot05webadmin.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器：登陆检查
 * 配置好拦截器要拦截那些请求
 * 把这些配置放在容器中
 */

/**
 * 编写一个拦截器实现HandlerInterceptor接口
 * 拦截器注册到容器中（实现WebMvcConfigurer接口的addInterceptors方法）
 * 指定拦截规则（如果拦截所有，静态资源也会被拦截）
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查是否登录逻辑
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
            //放行
            return true;
        } else {
            //拦截，然后跳转到登录界面
//            session.setAttribute("msg","请登录");
//            response.sendRedirect("/");
            request.setAttribute("msg", "请登录");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
    }

    /**
     * 目标方法执行完成之后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 页面渲染之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
