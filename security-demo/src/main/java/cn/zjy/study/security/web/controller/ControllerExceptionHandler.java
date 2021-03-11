package cn.zjy.study.security.web.controller;

import cn.zjy.study.security.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *  @Author: zjy
 *  @Date: 2021/3/11 10:04
 *  @Description: Controller增强器
 *  启动应用后，被 @ExceptionHandler、@InitBinder、@ModelAttribute 注解的方法，都会作用在 被 @RequestMapping 注解的方法上。
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Magical Sam");
    }

    /**
     *  @Author: zjy
     *  @Date: 2021/3/11 10:06
     *  @Description: 全局异常捕捉处理（此处仅处理UserNotExistException异常以及自定义返回的异常状态）
     *  '@ExceptionHandler' 拦截了异常，我们可以通过该注解实现自定义异常处理。
     *  其中，@ExceptionHandler 配置的 value 指定需要拦截的异常类型，上面拦截了 Exception.class 这种异常
     */
    @ExceptionHandler(UserNotExistException.class)// 抛出异常时，会转到此处处理
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)// 异常状态，例如500
    public Map<String,Object> handleUserNotExistException(UserNotExistException ex){
        Map<String, Object> result = new HashMap<>();
        result.put("id",ex.getId());
        result.put("message",ex.getMessage());
        return result;
    }
}
