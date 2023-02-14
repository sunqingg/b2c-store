package com.atguigu.clients;

import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("cart-service")
public interface CartClient {
    @PostMapping("/cart/check")
    public R check(Integer integer);
}
