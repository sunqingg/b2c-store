package com.atguigu.service.impl;

import com.atguigu.mapper.CarouselMapper;
import com.atguigu.pojo.Carousel;
import com.atguigu.service.CarouselService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    CarouselMapper carouselMapper;

    @Override
    public R list() {

        int limit= 4;
        IPage<Carousel> iPage = new Page<>(1,limit);
        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("priority");
        IPage<Carousel> selectPage = carouselMapper.selectPage(iPage, queryWrapper);

        List<Carousel> records = selectPage.getRecords();
        return R.ok(records);
    }
}
