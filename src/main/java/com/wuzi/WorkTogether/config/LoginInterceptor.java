package com.wuzi.WorkTogether.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/13 17:20
 * @lastEditor
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //首页、登录和注册页面放行
        if(request.getRequestURI().contains("register")||request.getRequestURI().contains("Login")||request.getRequestURI().contains("Register")||request.getRequestURI().contains("png")){
            return true;
        }
        //登陆过则拦截器放行
        if(session.getAttribute("userId")!=null){
            return true;
        }

        //不符合以上情况转去登录页
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
