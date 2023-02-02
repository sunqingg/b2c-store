package com.atguigu.clients;

import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("category-service")
public interface CategoryClient {

    @PostMapping("/category/hots")
    R hots(Category category);
}
