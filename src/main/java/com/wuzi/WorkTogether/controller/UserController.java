package com.wuzi.WorkTogether.controller;

import com.wuzi.WorkTogether.domain.User;
import com.wuzi.WorkTogether.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    /**
     * 登录功能
     * @param modelAndView
     * @param userTel
     * @param userPwd
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/checkLogin")
    public ModelAndView checkLogin(ModelAndView modelAndView, String userTel, String userPwd, HttpServletResponse response, HttpSession session) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        User user = userService.loginUser(userTel,userPwd);
        if (user != null)
        {
            Integer userId = user.getUserId();
//            RedirectView redirectView = new RedirectView("/WorkTogether/task/myTask/{userId}");
            RedirectView redirectView = new RedirectView("/WorkTogether/team/myTeam/{userTel}");
            modelAndView.setView(redirectView);
            session.setAttribute("userID",userId);
            modelAndView.addObject("userId", userId);
            modelAndView.addObject("userTel", userTel);
        }
        else {
            out.print("<script language=\"javascript\">alert(\"手机号或密码错误，请重新输入\");window.location.href='/WorkTogether/user/toLogin'</script>");
            wait(10);
            out.flush();
            out.close();
            RedirectView redirectView = new RedirectView("/WorkTogether/user/toLogin");
            modelAndView.setView(redirectView);
        }
        return modelAndView;
    }


    /**
     * 注册功能
     * @param user
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/register")
    public String register(User user,HttpServletResponse response) throws IOException,InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        user.setUserName("用户"+user.getUserTel());
        switch (userService.registerUser(user)){
            case 0: out.print("<script language=\"javascript\">alert(\"注册成功，欢迎您使用WorkTogether\");window.location.href='/WorkTogether/user/toLogin'</script>");
                    wait(10);
                    out.flush();
                    out.close();
                    return "login";
            case 1: out.print("<script language=\"javascript\">alert(\"该用户名已被注册，请重新选择一个用户名\");window.location.href='/WorkTogether/user/toRegister'</script>");
                    wait(10);
                    return "register";
            case 2: out.print("<script language=\"javascript\">alert(\"该手机号已被注册，请使用另外一个手机号\");window.location.href='/WorkTogether/user/toRegister'</script>");
                    wait(10);
                    out.flush();
                    out.close();
                    return "register";
            case 3: out.print("<script language=\"javascript\">alert(\"您输入的手机号格式不正确，请重新确认您的手机号\");window.location.href='/WorkTogether/user/toRegister'</script>");
                    wait(10);
                    out.flush();
                    out.close();
                    return "register";
            case 4: out.print("<script language=\"javascript\">alert(\"您输入的密码少于8位，请重新决定您的密码\");window.location.href='/WorkTogether/user/toRegister'</script>");
                    wait(10);
                    out.flush();
                    out.close();
                    return "register";
            default:out.print("<script language=\"javascript\">alert(\"服务器繁忙，请重试\");window.location.href='/WorkTogether/user/toRegister'</script>");
                    wait(10);
                    out.flush();
                    out.close();
                    return "register";
        }

    }

}
