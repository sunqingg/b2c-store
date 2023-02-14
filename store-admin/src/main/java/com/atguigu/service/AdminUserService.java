package com.atguigu.service;

import com.atguigu.param.AdminUserParam;
import com.atguigu.param.PagePram;
import com.atguigu.pojo.AdminUser;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Component;

//@Component
public interface AdminUserService extends IService<AdminUser> {
    R login(AdminUserParam adminUserParam);

    Object list(PagePram pagePram);

    R remove(AdminUser adminUser);

    R update(AdminUser adminUser);

    R save(User user);
}
