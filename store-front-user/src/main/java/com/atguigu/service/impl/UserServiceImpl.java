package com.atguigu.service.impl;

import com.atguigu.mapper.AddressMapper;
import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.pojo.address;
import com.atguigu.service.UserService;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.atguigu.constant.UserConstants.USER_SALT;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

//@Log4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;
    @Override
    public R check(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        Long aLong = userMapper.selectCount(queryWrapper);
        if (aLong == 0 ){
            return R.ok("账号可用");
        }else {
            return R.fail("账号不可用");
        }
    }

    /**
     * 用户注册功能
     *
     * @param user
     * @return
     */
    @Override
    public R register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        Long aLong = userMapper.selectCount(queryWrapper);
        if (aLong == 0 ){
            user.setPassword(MD5Util.encode(user.getPassword() + USER_SALT));
            userMapper.insert(user);
            return R.ok("注册成功");
        }else {
            return R.fail("账号不可用");
        }
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public R login(User user) {

        String userPassword = MD5Util.encode(user.getPassword() + USER_SALT);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        queryWrapper.eq("password",userPassword);
        User user2 = userMapper.selectOne(queryWrapper);


        if ( user2 == null ){
            return R.fail("登录失败");
        }else {
            User usernew = new User();
            usernew.setUserId(user2.getUserId());
            usernew.setUserName(user.getUserName());
            return R.ok("登录成功",usernew);
        }

    }

    /**
     * 通过用户的ID找出用户的信息
     *
     * @param user
     * @return
     */
    @Override
    public R addressList(User user) {
        QueryWrapper<address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());

        List<address> addresses = addressMapper.selectList(queryWrapper);
//        log.info(addresses);
        System.out.println(addresses);
        return R.ok("查询成功！",addresses);
    }

    @Override
    public R addressSave(address address) {
        QueryWrapper<address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",address.getUserId());

        addressMapper.insert(address);
        List<com.atguigu.pojo.address> addresses = addressMapper.selectList(queryWrapper);
        return R.ok(addresses);
    }

    @Override
    public R addressRemove(address address) {
        QueryWrapper<address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",address.getId());
        int i = addressMapper.deleteById(address.getId());

        if (i == 0 ){
            return R.fail("删除失败!");
        }else {
            return R.ok("删除成功!");
        }

    }
}
