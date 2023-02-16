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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
            cart.setId(cart1.getId());
            cart.setNum(num);
            log.info(cart.toString() + "--------"+ num.toString());
            cartMapper.updateById(cart);
            return R.ok("商品已存在,购物车数量+1");
        }



    }

    @Override
    public R list(Cart cart) {
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",cart.getUserId());

        List<Cart> cartList = this.list(cartQueryWrapper);
        log.info(cartList.toString());

        List<CartVo> cartVoList = new ArrayList<>();
        for (Cart cart1 : cartList) {
            Product product = productClient.id(cart1.getProductId());
            CartVo cartVo = new CartVo(product,cart1);
            cartVoList.add(cartVo);
        }
        return R.ok(cartVoList);
    }

    @Override
    public R update(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());

        this.update(cart,queryWrapper);
        return R.ok("修改购物车数量成功");
    }

    @Override
    public R remove(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());

        this.remove(queryWrapper);
        return R.ok("修改数量成功");
    }

    @Override
    public R check(Integer integer) {

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",integer);

        Long count = cartMapper.selectCount(queryWrapper);

        if (count == 0) {
            return R.ok("购物车没有此类商品");
        }
        return R.fail("购物车中存在要删除的商品");
    }
}

