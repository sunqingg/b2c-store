package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("collect")
public class Collect implements Serializable {
    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    // 必须是驼峰式的,如果直接写_的话,传值为null
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    private Long collectTime;
}
