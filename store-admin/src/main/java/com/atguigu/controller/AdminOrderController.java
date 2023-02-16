package com.atguigu.controller;

import com.atguigu.param.PageParam;
import com.atguigu.service.AdminOrderService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class AdminOrderController {

    @Autowired
    AdminOrderService adminOrderService;

    @GetMapping("list")
    public R list(PageParam param){
        return adminOrderService.list(param);
    }
}
