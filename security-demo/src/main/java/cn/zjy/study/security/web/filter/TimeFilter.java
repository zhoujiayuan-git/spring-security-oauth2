package cn.zjy.study.security.web.filter;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 *  @Author: zjy
 *  @Date: 2021/3/11 14:47
 *  @Description: 过滤器
 */
@Component
public class TimeFilter extends OncePerRequestFilter {

    // 缺点：只能那到http的请求和响应，并通过http中获取参数，Filter是在Servlet之前执行的，无法知道Spring中的内容，无法知道响应是从哪个控制器和方法中获取的
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Time Filter start");
        long start = new Date().getTime();
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        System.out.println("Time Filter 耗时：" + (new Date().getTime() - start));
        System.out.println("Time Filter finish");
    }
}
