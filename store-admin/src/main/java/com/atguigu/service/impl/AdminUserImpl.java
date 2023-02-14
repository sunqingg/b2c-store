package com.atguigu.service.impl;

import com.atguigu.clients.UserClient;
import com.atguigu.constant.UserConstants;
import com.atguigu.mapper.AdminUserMapper;
import com.atguigu.param.AdminUserParam;
import com.atguigu.param.PagePram;
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
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@Slf4j
public class AdminUserImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {


    @Autowired
    UserClient userClient;
    @Override
    public R login(AdminUserParam adminUserParam) {
        String newPwd = MD5Util.encode(adminUserParam.getUserPaassword() + UserConstants.USER_SALT);

        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",adminUserParam.getUserAccount());
        queryWrapper.eq("user_password",newPwd);

        log.info(newPwd);

        AdminUser user = this.getOne(queryWrapper);

        if (user == null) {
            return R.fail("账号或者密码错误");

        }
        return  R.ok("登陆成功",user);
    }

    @Override
    @Cacheable(value = "list.user",key= "#pageParam.currentPage+'-'+#pageParam.pageSize")
    public Object list(PagePram pagePram) {
        log.warn(pagePram.toString());
        R r = userClient.listPage(pagePram);
        return R.ok(r);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true),
                    @CacheEvict(value = "user",key="#adminUser.userId")
            }
    )
    public R remove(AdminUser adminUser) {
        R r = userClient.remove(adminUser.getUserId());

        return r;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true),
                    @CacheEvict(value = "user",key = "#adminUser.userId")
            }
    )
    public R update(AdminUser adminUser) {
        R update = userClient.update(adminUser);
        return update;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true),
                    @CacheEvict(value = "user",key = "user.userId")
            }
    )
    public R save(User user) {
        R save = userClient.save(user);
        return save;
    }


}
