package cn.zjy.study.security.controller;

import cn.zjy.study.security.dto.User;
import cn.zjy.study.security.dto.UserQueryCondition;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
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
     */
    @GetMapping(value = "{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(name = "id") String userId) {
        System.out.println("id：" + userId);
        User user = new User();
        user.setUserName("tom");
        return user;
    }




}
