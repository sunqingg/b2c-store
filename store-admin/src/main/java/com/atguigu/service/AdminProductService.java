package com.atguigu.service;

import com.atguigu.param.AdminProductParam;
import com.atguigu.param.SearchProductParam;
import com.atguigu.utils.R;

public interface AdminProductService {
    R list(SearchProductParam searchProductParam);

    R upload(AdminProductParam adminProductParam);


    R save(AdminProductParam adminProductParam);

    R update(AdminProductParam adminProductParam);

    R remove(AdminProductParam adminProductParam);
}
