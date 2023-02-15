package com.atguigu.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.clients.ProductClient;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.param.OrderParam;
import com.atguigu.param.PageParam;
import com.atguigu.param.ProductParam;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.Product;
import com.atguigu.service.OrderService;
import com.atguigu.utils.R;
import com.atguigu.vo.AdminOrderVo;
import com.atguigu.vo.CartVo;
import com.atguigu.vo.OrderVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ProductClient productClient;
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



        rabbitTemplate.convertAndSend("topic.ex","sub.number",productParams);
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);

        return R.ok("订单生成成功");

    }


    @Override
    public R list(Order order)  {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",order.getUserId());

//        this.list()

        List<Order> orderList = orderMapper.selectList(queryWrapper);
//        log.info(orderList.toString());
        List<OrderVo> orderVos = new ArrayList<>();
        for (Order order1 : orderList) {
//            try {
//                CartVo readValue = new ObjectMapper().readValue(JSON.toJSONString(order1), CartVo.class);
//                log.info(readValue.toString());
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
            log.info(JSON.toJSONString(order1));
            OrderVo parseObject = JSON.parseObject(JSON.toJSONString(order1), OrderVo.class);
            log.info(parseObject.toString());
            OrderVo orderVo = new OrderVo();

            orderVo.setProductName(productClient.id(order1.getProductId()).getProductName());
            orderVo.setProductPicture(productClient.id(order1.getProductId()).getProductPicture());
            orderVo.setId(order1.getId());
            orderVo.setOrderId(order1.getOrderId());
            orderVo.setOrderTime(order1.getOrderTime());
            orderVo.setProductNum(order1.getProductNum());
            orderVo.setProductId(order1.getProductId());
            orderVo.setProductPrice(order1.getProductPrice());
            orderVo.setUserId(order1.getUserId());


            orderVos.add(orderVo);
        }
        return R.ok(orderVos);
    }

    @Override
    public R pageList(PageParam param) {
//        IPage<Order> orderIPage = new Page<>(param.getCurrentPage(),param.getPageSize());
//        IPage<Order> page = orderMapper.selectPage(orderIPage, null);
//        List<Order> orderList = page.getRecords();
//        long total = page.getTotal();

        Long total = orderMapper.selectCount(null);


        List<AdminOrderVo> adminOrderVoList = orderMapper.selectAdminOrders(( param.getCurrentPage()-1 )*15+1, param.getPageSize());


        return R.ok("查询成功",adminOrderVoList,total);

    }
}
