package com.atguigu.controller;

import com.atguigu.pojo.Category;
import com.atguigu.pojo.Product;
import com.atguigu.service.ProductService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class CategoryClient {
    @Autowired
    ProductService productService;

    @PostMapping("promo")
    public R prome(@RequestBody @Validated Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.promo(category);
    }
}
