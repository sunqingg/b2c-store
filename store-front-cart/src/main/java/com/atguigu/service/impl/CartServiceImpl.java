package com.atguigu.service.impl;

import com.atguigu.clients.ProductClient;
import com.atguigu.mapper.CartMapper;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Product;
import com.atguigu.service.CartService;
import com.atguigu.utils.R;
import com.atguigu.vo.CartVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper,Cart> implements CartService {


    @Autowired
    ProductClient productClient;

    @Autowired
    CartMapper cartMapper;
    @Override
    public void removeBatchIds(List<Integer> cartIds) {
        List<Cart> carts = baseMapper.selectBatchIds(cartIds);

        this.removeBatchByIds(carts);

    }

    @Override
    public R save1(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());

        Long count = cartMapper.selectCount(queryWrapper);
        Cart cart1 = cartMapper.selectOne(queryWrapper);
        CartVo cartVo;

        if (count == 0) {
            Product product = productClient.id(cart.getProductId());
            cart.setNum(1);
            cartMapper.insert(cart);
            cartVo = new CartVo(product,cart);
            return R.ok("添加成功",cartVo);
        }else {
            Integer num = cart1.getNum() +1;
            cart.setNum(num);
            cartMapper.updateById(cart);
            return R.ok("商品已存在,购物车数量+1");
        }



    }
}

