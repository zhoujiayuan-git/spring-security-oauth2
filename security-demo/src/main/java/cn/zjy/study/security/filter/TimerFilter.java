package cn.zjy.study.security.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;

//@Component
public class TimerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Timer Filter init");

    }

    // 缺点：只能那到http的请求和响应，并通过http中获取参数，Filter是在Servlet之前执行的，无法知道Spring中的内容，无法知道响应是从哪个控制器和方法中获取的
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Timer Filter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Timer Filter:" + (new Date().getTime() - start));
        System.out.println("Timer Filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("Timer Filter destroy");
    }
}
