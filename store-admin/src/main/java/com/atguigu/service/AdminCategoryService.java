package com.atguigu.service;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;

public interface AdminCategoryService {

    R list(PageParam pageParam);

    R save(Category category);

    R update(Category category);

    R remove(Category category);
}
