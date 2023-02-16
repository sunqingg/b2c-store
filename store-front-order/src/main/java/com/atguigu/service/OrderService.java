package com.atguigu.service;

import com.atguigu.param.OrderParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Order;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService extends IService<Order> {

    R save(OrderParam orderParam);

    R list(Order order) throws JsonProcessingException;

    R pageList(PageParam param);
}
