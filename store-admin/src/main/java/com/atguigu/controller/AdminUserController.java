package com.atguigu.controller;

import com.atguigu.param.AdminUserParam;
import com.atguigu.param.PagePram;
import com.atguigu.pojo.AdminUser;
import com.atguigu.pojo.User;
import com.atguigu.service.AdminUserService;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("user")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    @PostMapping("login")
    public Object login(@Validated AdminUserParam adminUserParam, BindingResult bindingResult,
    HttpSession session) {
        if (bindingResult.hasErrors()) {
            return R.fail("登录失败,核心参数为null");
        }
        String varCode = adminUserParam.getVerCode();
        String captcha = (String) session.getAttribute("captcha");
        if (!varCode.equalsIgnoreCase(captcha)){
            return R.fail("登录失败,验证码错误");
        }
        //验证码通过往下
        R r =  adminUserService.login(adminUserParam);
        //获取数据存储到session共享域,后期登录访问判断.
        AdminUser adminUser = (AdminUser) r.getData();
        //存储到session共享域 key = userinfo 其他页面固定读取
        session.setAttribute("userInfo",adminUser);
        log.warn("-------------" + session.toString());
        return  r;

    }

    @GetMapping("logout")
    public Object logout(HttpSession session){
        session.invalidate();
        return R.ok("退出成功");
    }


    @GetMapping("list")
    @ResponseBody
    public Object list(PagePram pagePram){
        return adminUserService.list(pagePram);
    }

    @PostMapping("remove")
    public R remove(AdminUser adminUser,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("传参错误");
        }
        return adminUserService.remove(adminUser);
    }

    @PostMapping("update")
    public R update(@RequestBody AdminUser adminUser,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("传参错误");
        }
        return adminUserService.update(adminUser);
    }
    @PostMapping("save")
    public R save(@RequestBody User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("传参错误");
        }
        return adminUserService.save(user);
    }
}
