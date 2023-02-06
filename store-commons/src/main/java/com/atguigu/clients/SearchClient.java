package com.atguigu.clients;

import com.atguigu.param.SearchProductParam;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("search-service")
public interface SearchClient {

    @PostMapping("/search/product")
    R searchProduct(@RequestBody SearchProductParam searchProductParam);
}
