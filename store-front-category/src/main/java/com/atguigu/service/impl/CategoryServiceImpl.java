package com.atguigu.service.impl;

import com.atguigu.mapper.CategoryMapper;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.service.CategoryService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Category> alist() {
        List<Category> categoryList = categoryMapper.selectList(null);
        return categoryList;

    }

    @Override
    public R adminList(PageParam pageParam) {
        IPage<Category> categoryIPage = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        IPage<Category> page = categoryMapper.selectPage(categoryIPage, null);
        List<Category> categories = page.getRecords();
        long total = page.getTotal();
        return R.ok("查询成功",categories,total);
    }

    @Override
    public R adminRemove(Category category) {
        categoryMapper.deleteById(category);
        return R.ok("删除成功");
    }

    @Override
    public R adminSave(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());

        Long aLong = categoryMapper.selectCount(queryWrapper);
        if (aLong == 0) {
            categoryMapper.insert(category);
            return R.ok("添加成功");
        }
        return R.ok("类别已经存在.添加失败");
    }

    @Override
    public R adminUpdate(Category category) {
        categoryMapper.updateById(category);
        return R.ok("类别修改成功");
    }
}
