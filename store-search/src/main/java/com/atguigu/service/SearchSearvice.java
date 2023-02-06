package com.atguigu.service;

import com.atguigu.param.SearchProductParam;
import com.atguigu.utils.R;

public interface SearchSearvice {
    /**
     * 通过关键字搜索商品
     * @return
     */
    R searchProduct(SearchProductParam searchProductParam);
}
