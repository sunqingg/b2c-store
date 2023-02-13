package com.atguigu.controller;

import com.atguigu.pojo.Cart;
import com.atguigu.service.CartService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;



    @PostMapping("save")
    public R save1(@RequestBody Cart cart){
        return cartService.save1(cart);
    }

    @PostMapping("update")
    public R update(@RequestBody Cart cart){
        return cartService.update(cart);
    }

    @PostMapping("list")
    public R list(@RequestBody Cart cart){
        return cartService.list(cart);
    }
    @PostMapping("remove")
    public R remove(@RequestBody Cart cart){
        return cartService.remove(cart);
    }
}
