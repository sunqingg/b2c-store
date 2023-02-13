package com.atguigu.controller;

import com.atguigu.param.ByCategoryParam;
import com.atguigu.pojo.Category;
import com.atguigu.pojo.Product;
import com.atguigu.service.ProductService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("hots")
    public R hots(@RequestBody @Validated Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.hots(category);
    }
    @GetMapping ("category/list")
    public R clist() {
        return productService.clist();
    }

    @PostMapping("bycategory")
    public R bycategory(@RequestBody @Validated ByCategoryParam byCategoryParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.bycategory(byCategoryParam);
    }
    @PostMapping("all")
    public R all(@RequestBody @Validated ByCategoryParam byCategoryParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.bycategory(byCategoryParam);
    }

    @PostMapping("detail")
    public R detail(@RequestBody @Validated Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.detail(product);
    }

    @PostMapping("pictures")
    public R pictures(@RequestBody @Validated Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.pictures(product);
    }

    @PostMapping("search")
    public R search(@RequestBody @Validated ByCategoryParam byCategoryParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.bycategory(byCategoryParam);
    }
    @GetMapping("list")
    public List<Product> list(){
        return productService.list();
    }

    @PostMapping("ids")
    public List<Product> ids(@RequestBody List<Integer> list) {
        return productService.ids(list);
    }
    @PostMapping("id")
    public Product id(@RequestBody Integer integer) {
        return productService.id(integer);
    }

//    @PostMapping("id")
//    public Product id(@RequestBody Integer integer) {
//        return productService.id(integer);
//    }

}
