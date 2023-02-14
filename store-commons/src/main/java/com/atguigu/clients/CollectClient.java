package com.atguigu.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("collect-service")
public interface CollectClient {
    @PostMapping("/collect/removeByPid")
    public Object removeByPid(@RequestBody Integer productId);
}
