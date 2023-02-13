package com.atguigu.param;

import com.atguigu.vo.CartVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderParam {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("products")
    private List<CartVo> voList;
}
