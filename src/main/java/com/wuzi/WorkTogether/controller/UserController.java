package com.wuzi.WorkTogether.controller;

import com.wuzi.WorkTogether.domain.User;
import com.wuzi.WorkTogether.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("userDetail/{userId}")
    public String showUserDetail(@PathVariable Integer userId, Model model)
    {
        User user = userService.findUserById(userId);
        model.addAttribute(user);
        return "userDetail";
    }

    @RequestMapping("/userPwd/{userId}")
    public String showUserPwd(@PathVariable Integer userId, Model model)
    {
        User user = userService.findUserById(userId);
        model.addAttribute(user);
        return "changePwd";
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
    public ModelAndView checkLogin(ModelAndView modelAndView, String userTel, String userPwd,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        User user = userService.loginUser(userTel,userPwd);
        if (user != null)
        {
            String userId = user.getUserId().toString();
            RedirectView redirectView = new RedirectView("/WorkTogether/team/myTeam/{userTel}");
            modelAndView.setView(redirectView);
            session.setAttribute("userId",userId);
            session.setAttribute("userTel",userTel);
            System.out.println(userId+","+userTel);
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
     * @param userTel
     * @param userPwd
     * @param confirmUserPwd
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/register")
    public String register(String userTel,String userPwd,String confirmUserPwd,HttpServletResponse response) throws IOException,InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        switch (userService.registerUser(userTel,userPwd,confirmUserPwd)){
            case 0: out.print("<script language=\"javascript\">alert(\"注册成功，欢迎您使用WorkTogether\");window.location.href='/WorkTogether/user/toLogin'</script>");
                wait(10);
                return "login";
            case 1: out.print("<script language=\"javascript\">alert(\"该手机号已被注册，请使用另外一个手机号\");window.location.href='/WorkTogether/user/toRegister'</script>");
                wait(10);
                return "register";
            case 2: out.print("<script language=\"javascript\">alert(\"您输入的手机号格式不正确，请重新确认您的手机号\");window.location.href='/WorkTogether/user/toRegister'</script>");
                wait(10);
                return "register";
            case 3: out.print("<script language=\"javascript\">alert(\"您输入的密码少于8位，请重新决定您的密码\");window.location.href='/WorkTogether/user/toRegister'</script>");
                wait(10);
                return "register";
            case 4: out.print("<script language=\"javascript\">alert(\"您输入的两次密码不同，请重新输入\");window.location.href='/WorkTogether/user/toRegister'</script>");
                wait(10);
                return "register";
            default:out.print("<script language=\"javascript\">alert(\"服务器繁忙，请重试\");window.location.href='/WorkTogether/user/toRegister'</script>");
                wait(10);
                return "register";
        }
    }

    /**
     * 更改用户名
     * @param model
     * @param userId
     * @param newUserName
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/changeName/{userId}/{newUserName}")
    public String changeName(Model model,@PathVariable Integer userId,@PathVariable String newUserName,HttpServletResponse response) throws IOException,InterruptedException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(userService.changeUserName(newUserName,userId))
        {
            out.print("<script language=\"javascript\">alert(\"成功更改用户名！\");window.location.href='/WorkTogether/user/userDetail/" + userId + "'</script>");
        }
        else
        {
            out.print("<script language=\"javascript\">alert(\"该用户名已被使用，改名失败！\");window.location.href='/WorkTogether/user/userDetail/" + userId + "'</script>");
        }
        wait(10);
        return showUserDetail(userId,model);
    }


    /**
     * 更改用户名
     * @param model
     * @param userId
     * @param newUserPwd
     * @param response
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/changePwd/{userId}")
    public String changePwd(Model model,@PathVariable Integer userId, String userPwd,String newUserPwd,String confirmUserPwd, HttpServletResponse response) throws IOException,InterruptedException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        switch(userService.changeUserPwd(userPwd,userId,newUserPwd,confirmUserPwd))
        {
            case 0:
                out.print("<script language=\"javascript\">alert(\"成功更改密码！\");window.location.href='/WorkTogether/user/userDetail/" + userId + "'</script>");
                wait(10);
                return showUserDetail(userId,model);
            case 1:
                out.print("<script language=\"javascript\">alert(\"密码长度过短，请重新输入！\");window.location.href='/WorkTogether/user/userPwd/" + userId + "'</script>");
                wait(10);
                return showUserPwd(userId,model);
            case 2:
                out.print("<script language=\"javascript\">alert(\"新密码与原密码相同，更改失败\");window.location.href='/WorkTogether/user/userPwd/" + userId + "'</script>");
                wait(10);
                return showUserPwd(userId,model);
            case 3:
                out.print("<script language=\"javascript\">alert(\"原密码输入错误\");window.location.href='/WorkTogether/user/userPwd/" + userId + "'</script>");
                wait(10);
                return showUserPwd(userId,model);
            case 4:
                out.print("<script language=\"javascript\">alert(\"您输入的两次密码不同，请重新输入\");window.location.href='/WorkTogether/user/userPwd/" + userId + "'</script>");
                wait(10);
                return showUserPwd(userId,model);
            default:out.print("<script language=\"javascript\">alert(\"服务器繁忙，请重试\");window.location.href='/WorkTogether/user/userPwd/" + userId + "'</script>");
                wait(10);
                return showUserPwd(userId,model);
        }
    }

}