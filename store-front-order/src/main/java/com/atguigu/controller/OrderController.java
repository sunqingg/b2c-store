package com.atguigu.controller;

import com.atguigu.mapper.OrderMapper;
import com.atguigu.param.OrderParam;
import com.atguigu.pojo.Order;
import com.atguigu.service.OrderService;
import com.atguigu.utils.R;
import com.atguigu.vo.CartVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Autowired
    OrderMapper orderMapper;

    @PostMapping("save")
    public R save(@RequestBody @Validated OrderParam orderParam){
        return orderService.save(orderParam);
    }

    @PostMapping("list")
    public R list(@RequestBody Order order) throws JsonProcessingException {
        return orderService.list(order);
    }
    @PostMapping("check")
    public R check(@RequestBody Integer integer) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",integer);

        Long count = orderMapper.selectCount(queryWrapper);

        if (count == 0){
            return R.ok("没有需要删除的订单数据");
        }
        return R.fail("订单中有需要删除的商品,删除失败");
    }
}
