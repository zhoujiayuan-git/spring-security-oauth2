package cn.zjy.study.security.config;

import cn.zjy.study.security.web.filter.TimeFilter;
import cn.zjy.study.security.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  @Author: zjy
 *  @Date: 2021/3/11 11:14
 *  @Description: WebMvcConfigurerAdapter Spring内部的一种配置方式
 * 采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    // 相当于在过滤器上添加@Component,将TimeFilter加入到SpringBoot的项目中
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        // 注册过滤器
        registrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        // 配置过滤器对哪些路径起作用
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

    /**
     * @Description 添加拦截器
     * @Author  zjy
     * @Date   2021/3/11 11:15
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    /*@Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // 注册拦截器
        configurer.registerCallableInterceptors();
    }*/
}
