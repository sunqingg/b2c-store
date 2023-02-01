package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.pojo.address;
import com.atguigu.service.UserService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("check")
    public R check(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            R.fail("传参数错误！");
        }
        return userService.check(user);
    }
    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            R.fail("传参数错误！");
        }
        return userService.register(user);
    }
    @PostMapping("login")
    public R login(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            R.fail("传参数错误！");
        }
        return userService.login(user);
    }

    @PostMapping("address/list")
    public R addressList(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            R.fail("传参数错误！");
        }
        return userService.addressList(user);
    }

    @PostMapping("address/save")
    public R addressSave(@RequestBody @Validated address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            R.fail("传参数错误！");
        }
        return userService.addressSave(address);
    }

    @PostMapping("address/remove")
    public R addressRemove(@RequestBody @Validated address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            R.fail("传参数错误！");
        }
        return userService.addressRemove(address);
    }
}
