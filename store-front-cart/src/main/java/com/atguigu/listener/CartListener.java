package com.atguigu.listener;

import com.atguigu.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartListener {

    @Autowired
    private CartService cartServive;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("clear.queue"),
            exchange = @Exchange("topic.ex"),
            key = "clear.cart"
    ))
    public void clear(List<Integer> cartIds){
        System.out.println("CartListener.subNumber");
        System.out.println("cartIds = " + cartIds);

        cartServive.removeBatchIds(cartIds);
    }
}
