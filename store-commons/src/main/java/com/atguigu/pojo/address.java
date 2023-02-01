package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@TableName("address")
public class address {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String linkman;
    private String phone;
    private String address;
    @JsonProperty("user_id")
    private Integer userId;
}
