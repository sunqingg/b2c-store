package com.atguigu.service;

import com.atguigu.param.AdminProductParam;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.SearchProductParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;

public interface AdminProductService {
    R list(SearchProductParam searchProductParam);

    R upload(AdminProductParam adminProductParam);


    R save(ProductSaveParam adminProductParam);

    R update(Product product);

    R remove(Product product);
}
