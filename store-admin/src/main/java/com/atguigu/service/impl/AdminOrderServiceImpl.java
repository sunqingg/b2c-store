package com.atguigu.service.impl;

import com.atguigu.clients.OrderClient;
import com.atguigu.param.PageParam;
import com.atguigu.service.AdminOrderService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    @Autowired
    OrderClient orderClient;

    @Cacheable(value ="list.order",key =
            "#param.currentPage+'-'+#param.pageSize")
    @Override
    public R list(PageParam param) {
        return orderClient.pageList(param);
    }
}
