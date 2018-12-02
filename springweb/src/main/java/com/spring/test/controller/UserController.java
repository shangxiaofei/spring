package com.spring.test.controller;

import com.spring.test.service.UserService;
import com.spring.test.entry.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午12:31 2018/5/13
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request, HttpServletResponse response){
        System.out.println("=================addUser start=======================");
        com.mysql.jdbc.Driver d;
        User user=new User();
        user.setName("中华人民共和国");
        user.setAddress("地球");
        user.setAge(100000);
        userService.addUser(user);
        System.out.println("=================addUser end=======================");

        return "index";
    }


}
