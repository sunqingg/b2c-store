package com.atguigu.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.err.println("LoginProtectInterceptor.preHandle");

        if (null == request.getSession().getAttribute("userInfo")){
            response.sendRedirect(request.getContextPath()  + "/login.html");
            return false;
        }else {
            return true;
        }
    }
}
