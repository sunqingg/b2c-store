package com.atguigu.clients;

import com.atguigu.param.PagePram;
import com.atguigu.pojo.AdminUser;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")


public interface UserClient {


    @PostMapping("/usr/list")
    R listPage(@RequestBody PagePram pagePram);

    @PostMapping("/usr/remove")
    R remove(Integer userId);

    @PostMapping("/user/update")
    R update(AdminUser adminUser);

    @PostMapping("/user/save")
    R save(User user);
}
