package com.atguigu.clients;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/user/list")
    public R list(@RequestBody PageParam param);

    @PostMapping("/user/remove")
    public R remove(@RequestBody User user);

    @PostMapping("/user/update")
    public R update(@RequestBody User user);

    @PostMapping("/user/save")
    public R save(@RequestBody User user);
}
