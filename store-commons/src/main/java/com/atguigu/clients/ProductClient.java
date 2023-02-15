package com.atguigu.clients;

import com.atguigu.param.ProductSaveParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/product/list")
    List<Product> list();

    @PostMapping("/product/ids")
    List<Product> ids(List<Integer> list);

    @PostMapping("/product/id")
    Product id(Integer integer);

    @PostMapping("/product/save")
    R save(@RequestBody ProductSaveParam saveParam);
    @PostMapping("/product/update")
    public R update(Product product);
    @PostMapping("/product/remove")
    public R remove(@RequestBody Product product);
}
