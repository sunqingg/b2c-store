package com.atguigu.service.impl;

import com.atguigu.clients.ProductClient;
import com.atguigu.mapper.CollectMapper;
import com.atguigu.pojo.Collect;
import com.atguigu.pojo.Product;
import com.atguigu.service.CollectorService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    ProductClient productClient;

    @Override
    public R save(Collect collect) {
        Integer product_id = collect.getProductId();
        Integer user_id = collect.getUserId();
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id).eq("product_id",product_id);

        Long count = collectMapper.selectCount(queryWrapper);
        if (count > 0){
            return R.fail("shangpinyitianjia");
        }

        collect.setCollectTime(System.currentTimeMillis());
        collectMapper.insert(collect);
        return R.ok("tianjiachenggong");
    }

    @Override
    public R list(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());

        List<Collect> collects = collectMapper.selectList(queryWrapper);
//        Collect collectone = collectMapper.selectOne(queryWrapper);
//        log.info(collect.toString());
//        log.info(collects.toString());
        List<Integer> list = new ArrayList<>();

        for (Collect collect1 : collects) {
            list.add(collect1.getProductId());
        }
        List<Product> products = productClient.ids(list);

        return R.ok(products);
    }

    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());

        collectMapper.delete(queryWrapper);
        return R.ok("shanchuchenggong");
    }
}
