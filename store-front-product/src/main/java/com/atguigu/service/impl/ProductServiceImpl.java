package com.atguigu.service.impl;

import com.atguigu.clients.CategoryClient;
import com.atguigu.mapper.ProductMapper;
import com.atguigu.pojo.Category;
import com.atguigu.pojo.Product;
import com.atguigu.service.ProductService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@Service

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryClient categoryClient;

    @Override
    public R promo(Category category) {
        List<Category> data = (List<Category>) categoryClient.hots(category).getData();
        System.out.println(data);


        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id",data);
        queryWrapper.orderByDesc("product_sales");

        List<Product> products = productMapper.selectList(queryWrapper);
        return R.ok(products);
    }
}
