package com.atguigu.service.impl;

import com.atguigu.clients.UserClient;
import com.atguigu.constant.UserConstants;
import com.atguigu.mapper.AdminUserMapper;
import com.atguigu.param.AdminParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.AdminUser;
import com.atguigu.pojo.User;
import com.atguigu.service.AdminUserService;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    UserClient userClient;


    @Override
    public R login(AdminParam AdminParam) {
//        System.err.println();
        //密码加密处理
        //代码加密处理,注意加盐,生成常量
        String newPwd = MD5Util.encode(AdminParam.getUserPassword() +
                UserConstants.USER_SALT);
        log.info(newPwd.toString());

        //数据库登录查询
        QueryWrapper<AdminUser> adminUserQueryWrapper =
                new QueryWrapper<>();

        adminUserQueryWrapper.eq("user_account",AdminParam.getUserAccount());
        adminUserQueryWrapper.eq("user_password",newPwd);

        AdminUser adminUser = this.getOne(adminUserQueryWrapper);
        //结果封装

        if (adminUser == null) {
            return R.fail("账号或者密码错误!");
        }

        R ok = R.ok("用户登录成功!", adminUser);
        log.info("AdminUserServiceImpl.login业务结束，结果:{}",ok);

        return ok;
    }
    @Cacheable(value = "list.user",key = "#param.currentPage+'-'+#param.pageSize")
    @Override
    public R list(PageParam param) {
        return userClient.list(param);

    }
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true)
//                    , @CacheEvict(value = "user",key = "#user.userId" )
            }
    )
    @Override
    public R remove(Integer userId) {

        return userClient.remove(userId);
    }
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true)
//                    , @CacheEvict(value = "user",key = "#user.userId" )
            }
    )
    @Override
    public R update(User user) {
        return userClient.update(user);
    }
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true)
//                    , @CacheEvict(value = "user",key = "#user.userId")
            }
    )
    @Override
    public R save(User user) {
        return userClient.save(user);
    }
}