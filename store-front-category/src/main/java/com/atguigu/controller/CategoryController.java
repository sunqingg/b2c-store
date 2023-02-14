package com.atguigu.controller;

import com.atguigu.pojo.Category;
import com.atguigu.service.CategoryService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("clist")
    public List<Category> clist(){
        return categoryService.clist();
    }
}
