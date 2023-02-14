package com.atguigu.service.impl;

import com.atguigu.clients.SearchClient;
import com.atguigu.param.AdminProductParam;
import com.atguigu.param.SearchProductParam;
import com.atguigu.service.AdminProductService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;

@Service
public class AdminProductServiceImpl implements AdminProductService {

    @Autowired
    SearchClient searchClient;


    @Override
    public R list(SearchProductParam searchProductParam) {
        return searchClient.searchProduct(searchProductParam);
    }

    @Override
    public R save(AdminProductParam adminProductParam) {
        return null;
    }

    @Override
    public R update(AdminProductParam adminProductParam) {
        return null;

    }

    @Override
    public R upload(AdminProductParam adminProductParam) {
        return null;
    }

    @Override
    public R remove(AdminProductParam adminProductParam) {
        return null;
    }

}
