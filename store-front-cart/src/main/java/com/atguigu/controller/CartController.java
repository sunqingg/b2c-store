package com.atguigu.controller;

import com.atguigu.pojo.Cart;
import com.atguigu.service.CartService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;



    @PostMapping("save")
    public R save1(@RequestBody Cart cart){
        return cartService.save1(cart);
    }

    @PostMapping("list")
    public R list(@RequestBody Cart cart,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return  R.fail("传参数错误");
        }
        return cartService.list(cart);
    }

    @PostMapping("update")
    public R update(@RequestBody Cart cart,BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return R.fail("传参错误");
        }
        return cartService.update(cart);
    }

    @PostMapping("remove")
    public R remove(@RequestBody Cart cart,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("传参错误");
        }
        return cartService.remove(cart);
    }

    @PostMapping("check")
    public R check(Integer integer) {
        return cartService.check(integer);
    }

}
