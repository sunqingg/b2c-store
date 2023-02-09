package com.atguigu.param;

import com.atguigu.vo.CartVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderParam {
    private Integer userId;
    private List<CartVo> voList;
}
