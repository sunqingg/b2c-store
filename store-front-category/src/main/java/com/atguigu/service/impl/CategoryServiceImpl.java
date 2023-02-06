package com.atguigu.service.impl;

import com.atguigu.mapper.CategoryMapper;
import com.atguigu.pojo.Category;
import com.atguigu.service.CategoryService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Watchable;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public R byName(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());
        Category selectOne = categoryMapper.selectOne(queryWrapper);
        return R.ok("查询成功",selectOne);
    }

    @Override
    public R hots(Category category) {
        return null;
    }

    @Override
    public R list() {
        List<Category> categories = categoryMapper.selectList(null);
        return R.ok("查询成功",categories);
    }
}
