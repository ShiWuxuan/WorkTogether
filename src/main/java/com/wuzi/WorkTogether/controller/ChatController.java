package com.wuzi.WorkTogether.controller;

import com.wuzi.WorkTogether.domain.vo.ChatUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 王子皓
 * @title
 * @Package com.wuzi.WorkTogether.controller
 * @date 2020/12/14 20:10
 */
@Controller
@RequestMapping("/chatroom")
public class ChatController {

    @RequestMapping("/enter/{userId}/{userName}")
    public ModelAndView enterChatroom(@PathVariable Integer userId, @PathVariable String userName,
                                      HttpServletRequest request){
        HttpSession session = request.getSession();
        // 判断是否是一个已经在聊天室的用户，没有则创建
        if (null != session.getAttribute("loginUser")) {
            // 清除旧的用户
            session.removeAttribute("loginUser");
        }
        // 构建一个用户
        ChatUser loginUser = new ChatUser();
        loginUser.setId(userId.toString());
        loginUser.setNickname(userName);
        // 将用户放入session
        session.setAttribute("loginUser", loginUser);

        // 将登录信息放入数据库，便于协查跟踪聊天者
        System.out.println("新用户诞生了：" + loginUser);
        return new ModelAndView("chatroom");
    }
}
