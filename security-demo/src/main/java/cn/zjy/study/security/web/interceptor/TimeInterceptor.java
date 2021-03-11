package cn.zjy.study.security.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *  @Author: zjy
 *  @Date: 2021/3/11 14:47
 *  @Description: 拦截器
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    // 控制器的方法被调用之前调用此方法
    // 缺点：在调用控制器的方法之前，我们无法知道调用方法的请求参数
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle");
        System.out.println(((HandlerMethod)o).getBean().getClass().getName());
        System.out.println(((HandlerMethod)o).getMethod().getName());
        httpServletRequest.setAttribute("startTime",new Date().getTime());
        return true;// 是否调用下面的方法
    }

    // 控制器的方法处理之后调用此方法，但是如果控制器方法抛出异常，不会调用此方法
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Long startTime = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+(new Date().getTime()-startTime));
    }

    // 只要控制器的方法处理之后,无论是否成功都调用此方法
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        Long startTime = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+(new Date().getTime()-startTime));
        System.out.println("ex is:"+e);
    }
}
