package com.atguigu.service;

import com.atguigu.param.AdminParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.AdminUser;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Component;

//@Component
public interface AdminUserService extends IService<AdminUser> {
    R login(AdminParam adminParam);

    R list(PageParam param);

    R remove(User user);

    R update(User user);

    R save(User user);
}
