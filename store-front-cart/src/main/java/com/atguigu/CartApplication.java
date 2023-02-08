package com.atguigu;

import com.atguigu.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = ProductClient.class)
@MapperScan("com.atguigu.mapper")
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);

    }
}