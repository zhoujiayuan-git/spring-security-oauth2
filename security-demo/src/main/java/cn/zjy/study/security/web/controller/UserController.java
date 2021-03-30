package cn.zjy.study.security.web.controller;

import cn.zjy.study.security.dto.User;
import cn.zjy.study.security.dto.UserQueryCondition;
import cn.zjy.study.security.exception.UserNotExistException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController//标明此Controller提供RestApi
@RequestMapping("/user")
public class UserController {

    /*@RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> query(@RequestParam(name = "userName",defaultValue = "tom",required = false) String nickName) {
        System.out.println(nickName);
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }*/

    @GetMapping
    @JsonView(User.UserSimpleView.class)// 返回相同对象的时候，在不同视图下显示不同的字段
    @ApiOperation(value = "查询用户信息")// 为swagger提供注释
    public List<User> query(UserQueryCondition userQueryCondition) {
        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    /**
     * @Author: zjy
     * @Date: 2021/3/9 13:45
     * @Description: @PathVariable url中声明的参数，可以获取到
     * 通过正则表达式，校验请求参数的格式
     */
    @GetMapping(value = "{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(name = "id") String userId) {
        System.out.println("id：" + userId);
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUserName("tom");
        return user;
        // 如果抛出此自定义异常，会通过ControllerExceptionHandler处理掉异常，那么不会在拦截器的afterCompletion中获取到
//        throw new UserNotExistException(userId);
        // 如果未抛出异常，就会在在拦截器的afterCompletion中获取到
//        int a = 1/0;
    }

    @PostMapping
    public User create(@Validated @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping(value = "{id:\\d+}")
    public User update(@Validated @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        return user;
    }

    // @ApiParam(value = "用户Id") 为swagger提供注释
    @DeleteMapping(value = "{id:\\d+}")
    public void delete(@PathVariable @ApiParam(value = "用户Id") String id) {
        System.out.println(id);
    }


}
