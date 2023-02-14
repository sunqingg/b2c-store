package com.atguigu.controller;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.service.CategoryService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("hots")
    public R byName(@RequestBody Category category, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return R.fail("传参错误");
        }
        return categoryService.byName(category);
    }
    @GetMapping("list")
    public R list(){
        return categoryService.list();
    }

    @GetMapping("alist")
    public List<Category> alist(){
        return categoryService.alist();

    }
    @GetMapping("adminList")
    public R adminList(PageParam pageParam) {
        return categoryService.adminList(pageParam);
    }

    @PostMapping("adminSave")
    public R adminSave(@RequestBody Category category){
        return categoryService.adminSave(category);
    }
    @PostMapping("adminUpdate")
    public R adminUpdate(@RequestBody Category category){
        return categoryService.adminUpdate(category);
    }
    @PostMapping("adminRemove")
    public R adminRemove(@RequestBody Category category){
        return categoryService.adminRemove(category);
    }

}
