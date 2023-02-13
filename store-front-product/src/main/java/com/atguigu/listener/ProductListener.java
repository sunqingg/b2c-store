package com.atguigu.listener;

import com.atguigu.param.ProductParam;
import com.atguigu.service.ProductService;
import io.lettuce.core.dynamic.annotation.Key;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("xx.queue"),
            exchange = @Exchange("topic.ex"),
            key = "sub.number"
    ))
    public void subNumber(List<ProductParam> productParams)  {
        System.err.println("ProductListener.subNumber");
        System.err.println("ProductNumberParams" +productParams);
//        log.info(productParams.toString());
        productService.batchNum(productParams);

    }
}
