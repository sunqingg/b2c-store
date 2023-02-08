package com.atguigu.listener;

import com.atguigu.param.ProductParam;
import com.atguigu.service.ProductService;
import io.lettuce.core.dynamic.annotation.Key;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductListener {
    private ProductService productService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("sub.queue"),
            exchange = @Exchange("topic.ex"),
            key = "sub.number"
    ))
    public void subNumber(List<ProductParam> productParams) {
        System.err.println("ProductListener.subNumber");
        System.err.println("ProductNumberParams" +productParams);
        productService.batchNum(productParams);

    }
}