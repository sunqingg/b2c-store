package com.atguigu.controller;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.service.AdminCategoryService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class AdminCategoryController {
    @Autowired
    AdminCategoryService adminCategoryService;

    @GetMapping("list")
    public R list(PageParam pageParam) {
        return adminCategoryService.list(pageParam);
    }
    @PostMapping("save")
    public R save(Category category) {
        return adminCategoryService.save(category);
    }
    @PostMapping("update")
    public R update(Category category) {
        return adminCategoryService.update(category);
    }
    @PostMapping("remove")
    public R remove(Category category) {
        return adminCategoryService.remove(category);
    }

}
