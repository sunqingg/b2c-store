package com.atguigu.controller;

import com.atguigu.param.SearchProductParam;
import com.atguigu.service.SearchSearvice;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    SearchSearvice searchSearvice;

    @PostMapping("product")
    public R searchProduct(@RequestBody SearchProductParam searchProductParam, BindingResult bindResult){
        if (bindResult.hasErrors()) {
            return R.fail("传参错误");
        }
        return searchSearvice.searchProduct(searchProductParam);
    }
    @PostMapping("test")
    public String test(){
        return "test";
    }

}
