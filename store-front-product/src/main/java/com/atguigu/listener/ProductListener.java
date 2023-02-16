package com.atguigu.listener;

import com.atguigu.param.ProductParam;
import com.atguigu.service.ProductService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductListener {
    @Autowired
    private ProductService productService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "qq.queue"),
            exchange = @Exchange("topic.ex"),
            key = "sub.number"
    ))
    public void subNumber(List<ProductParam> productParams) {
        System.err.println("ProductListener.subNumber");
        System.err.println("ProductNumberParams" + productParams);
        productService.batchNum(productParams);

    }
}
