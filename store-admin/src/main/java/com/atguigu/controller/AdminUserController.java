package com.atguigu.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.param.AdminParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.AdminUser;
import com.atguigu.pojo.User;
import com.atguigu.service.AdminUserService;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("user")
public class AdminUserController {

    @Autowired
    AdminUserService adminUserService;

    @PostMapping("login")
    @ResponseBody
    public Object login(@Validated AdminParam AdminParam, BindingResult bindingResult, HttpSession session){

        //参数校验
        if (bindingResult.hasErrors()) {
            log.info("AdminUserController.login业务,参数异常!");
            return R.fail("登录失败,核心参数为null");
        }
        System.err.println(JSON.toJSONString(AdminParam));
        //校验验证码
        String varCode = AdminParam.getVerCode();
        String captcha = (String) session.getAttribute("captcha");
//        log.error(session.toString());
        System.err.println(session.getAttributeNames());
        if (!varCode.equalsIgnoreCase(captcha)){

            return R.fail("登录失败,验证码错误!");
        }
        //验证码通过验证数据
        R r = adminUserService.login(AdminParam);

        //获取数据存储到session共享域,后期登录访问判断
        AdminUser adminUser = (AdminUser) r.getData();
        //存储到session共享域  key = userInfo 其他页面固定读取
        session.setAttribute("userInfo",adminUser);
        log.info(session.getAttributeNames().toString());
        return r;
    }

    @GetMapping("logout")
    @ResponseBody
    public Object logout(HttpSession session){
        //清空session
        session.invalidate();

        return R.ok("退出登录成功!");
    }

    @GetMapping("list")
    @ResponseBody
    public R list(PageParam param) {
        return adminUserService.list(param);
    }

    @PostMapping ("remove")
    @ResponseBody
    public R remove(Integer userId) {
        return adminUserService.remove(userId);
    }

    @PostMapping ("update")
    @ResponseBody
    public R update(User user) {
        return adminUserService.update(user);
    }

    @PostMapping ("save")
    @ResponseBody
    public R save(User user) {
        return adminUserService.save(user);
    }
}
