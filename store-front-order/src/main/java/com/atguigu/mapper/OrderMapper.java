package com.atguigu.mapper;

import com.atguigu.pojo.Order;
import com.atguigu.vo.AdminOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderMapper extends BaseMapper<Order> {
    List<AdminOrderVo> selectAdminOrders(@Param("offset") int offset, @Param("number")int number);


}
