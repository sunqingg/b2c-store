package com.atguigu.controller;

import com.atguigu.param.OrderParam;
import com.atguigu.service.OrderService;
import com.atguigu.utils.R;
import com.atguigu.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("save")
    public R save(@RequestBody @Validated OrderParam orderParam){
        return orderService.save(orderParam);
    }

}
