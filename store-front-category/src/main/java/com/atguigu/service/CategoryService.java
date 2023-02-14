package com.atguigu.service;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;

import java.util.List;

public interface CategoryService {
    R hots(Category category);

    R byName(Category category);

    R list();

    List<Category> alist();

    R adminList(PageParam pageParam);

    R adminSave(Category category);

    R adminUpdate(Category category);

    R adminRemove(Category category);
}
