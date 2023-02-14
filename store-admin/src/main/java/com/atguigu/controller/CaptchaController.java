package com.atguigu.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping
public class CaptchaController {
    @RequestMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //默认四个字符长度的验证码
        //默认放入session key = captcha 的位置
        CaptchaUtil.out(request, response);
    }
}
