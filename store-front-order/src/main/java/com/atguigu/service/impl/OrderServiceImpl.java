package com.atguigu.service.impl;

import com.atguigu.mapper.OrderMapper;
import com.atguigu.param.OrderParam;
import com.atguigu.param.ProductParam;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.Product;
import com.atguigu.service.OrderService;
import com.atguigu.utils.R;
import com.atguigu.vo.CartVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Transactional
    @Override
    public R save(OrderParam orderParam) {
        Integer userId = orderParam.getUserId();
        List<CartVo> voList = orderParam.getVoList();
        long ctime = System.currentTimeMillis();
        List<Integer> cartIds = new ArrayList<>();

        List<Order> orderList = new ArrayList<>();
        List<ProductParam> productParams = new ArrayList<>();

        for (CartVo cartVo : voList) {
            cartIds.add(cartVo.getId());
            Order order = new Order();
//            order.setId(cartVo.getId());
            order.setOrderId(ctime);
            order.setUserId(userId);
            order.setProductId(cartVo.getProductId());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            order.setOrderTime(ctime);

            orderList.add(order);

            ProductParam productParam = new ProductParam();
            productParam.setProductId(cartVo.getProductId());
            productParam.setProductNum(cartVo.getNum());
            productParams.add(productParam);
        }
        this.saveBatch(orderList);
        log.info(productParams.toString()+ "----" + cartIds);
        rabbitTemplate.convertAndSend("topic.ex","sub.number",productParams);
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);

        return R.ok("订单生成成功");

    }
}
