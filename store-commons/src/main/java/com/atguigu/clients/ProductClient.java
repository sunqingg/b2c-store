package com.atguigu.clients;

import com.atguigu.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/product/list")
    List<Product> list();

    @PostMapping("/product/ids")
    List<Product> ids(List<Integer> list);
}
