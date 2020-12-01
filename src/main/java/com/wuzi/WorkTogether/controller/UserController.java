package com.wuzi.WorkTogether.controller;

import com.wuzi.WorkTogether.domain.User;
import com.wuzi.WorkTogether.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 张朝勋
 * @version 1.0
 * @date 2020/12/1 11:40
 * @lastEditor
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister()
    {
        return "register";
    }

    @RequestMapping("/toLogin")
    public String toLogin()
    {
        return "login";
    }

    @RequestMapping("/checkLogin")
    public String checkLogin(String userName, String userPwd, HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(userService.loginUser(userName,userPwd) != null)
            return "myTask";
        else {
            out.print("<script language=\"javascript\">alert(\"用户名或密码错误，请重新输入\");window.location.href='/WorkTogether/user/toLogin'</script>");
            wait(100);
            return "login";
        }
    }



    //注册功能
    @RequestMapping("/register")
    public String register(User user,HttpServletResponse response) throws IOException,InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        switch (userService.registerUser(user)){
            case 0: out.print("<script language=\"javascript\">alert(\"注册成功，欢迎您使用WorkTogether\");window.location.href='/WorkTogether/user/toLogin'</script>");
                    wait(100);
                    return "login";
            case 1: out.print("<script language=\"javascript\">alert(\"该用户名已被注册，请重新选择一个用户名\");window.location.href='/WorkTogether/user/toRegister'</script>");
                    wait(100);
                    return "register";
            case 2: out.print("<script language=\"javascript\">alert(\"该手机号已被注册，请使用另外一个手机号\");window.location.href='/WorkTogether/user/toRegister'</script>");
                    wait(100);
                    return "register";
            default:out.print("<script language=\"javascript\">alert(\"服务器繁忙，请重试\");window.location.href='/WorkTogether/user/toRegister'</script>");
                    wait(100);
                    return "register";
        }

    }

}
