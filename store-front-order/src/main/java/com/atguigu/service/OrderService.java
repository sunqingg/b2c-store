package com.atguigu.service;

import com.atguigu.param.OrderParam;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.utils.R;
import com.atguigu.vo.CartVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {

    R save(OrderParam orderParam);
}
