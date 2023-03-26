package org.example.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class FilterDemo2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("2、FilterDemo...");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("3、FilterDemo...");
    }

    @Override
    public void destroy() {

    }
}
