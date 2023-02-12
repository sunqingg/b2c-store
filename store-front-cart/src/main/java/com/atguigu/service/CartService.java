package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.utils.R;

import java.util.List;

public interface CartService {



    void removeBatchIds(List<Integer> cartIds);


//    R save(Cart cart);

    R save1(Cart cart);

    R list(Cart cart);

    R update(Cart cart);

    R remove(Cart cart);
}
