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
//            log.info(cart1.getNum().toString() + "-------"+ num.toString());
            cart.setNum(num);
//            log.info(cart.toString());
//            cartMapper.updateById(cart1);
            cartMapper.update(cart,queryWrapper);
            return R.ok("商品已存在,购物车数量+1");
        }



    }

    @Override
    public R update(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",cart.getProductId());
        queryWrapper.eq("user_id",cart.getUserId());
//        queryWrapper.eq("num",cart.getNum());

        Product product = productClient.id(cart.getProductId());

//        Assert.isTrue(product != null, "商品不存在添加失败");
//        Assert.isTrue(cart.getNum() <= product.getProductNum(), "库存不足");
        if (product == null){
            return R.fail("商品不存在添加失败");
        } else if (cart.getNum() > product.getProductNum()) {
            return R.fail("库存不足");
        }else {
            Cart selectOne = cartMapper.selectOne(queryWrapper);
            selectOne.setNum(cart.getNum());
            cartMapper.updateById(selectOne);
            return R.ok("购物车添加成功");
        }
    }

    @Override
    public R list(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.select("product_id");

        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        List<Integer> list = new ArrayList<>();
        for (Cart cart1 : cartList) {
            list.add(cart1.getProductId());
        }
        List<Product> products = productClient.ids(list);
        return R.ok(products);
    }

    @Override
    public R remove(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());

        cartMapper.delete(queryWrapper);
        return R.ok("删除购物车成功");
    }


}

