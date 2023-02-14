package com.atguigu.service;

import com.atguigu.param.PagePram;
import com.atguigu.pojo.AdminUser;
import com.atguigu.pojo.User;
import com.atguigu.pojo.address;
import com.atguigu.utils.R;

public interface UserService {

    /**
     * 检查用户创建账号是否可用
     * @param user 传入的用户信息
     * @return 返回 R
     */
    R check(User user);

    /**
     * 用户注册功能
     * @param user
     * @return
     */
    R register(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    R login(User user);

    /**
     * 通过用户的ID找出用户的信息
     * @param user
     * @return
     */
    R addressList(User user);

    R addressSave(address address);

    R addressRemove(address address);

    Object listPage(PagePram pagePram);

    R remove(Integer integer);

    R update(AdminUser adminUser);

    R save(User user);
}
