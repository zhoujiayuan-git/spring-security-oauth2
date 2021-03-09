package cn.zjy.study.security.config;

import cn.zjy.study.security.filter.TimerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig {

    // 相当于在过滤器上添加@@Component,将TimerFilter加入到SpringBoot的项目中
    @Bean
    public FilterRegistrationBean timerFilter() {
        // 注册过滤器
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimerFilter timerFilter = new TimerFilter();
        registrationBean.setFilter(timerFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
}
