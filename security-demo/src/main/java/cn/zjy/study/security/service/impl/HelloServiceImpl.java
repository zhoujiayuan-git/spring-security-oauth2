package cn.zjy.study.security.service.impl;

import cn.zjy.study.security.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        return "hello" + name;
    }
}
