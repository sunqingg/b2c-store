package com.atguigu.controller;

import com.atguigu.pojo.Carousel;
import com.atguigu.service.CarouselService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @PostMapping("list")
    public R list() {
//        if (bindingResult.hasErrors()) {
//            return R.fail("传参错误");
//
//        }
        return carouselService.list();
    }
}
