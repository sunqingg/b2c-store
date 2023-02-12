package com.atguigu.vo;

import com.atguigu.pojo.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.aspectj.apache.bcel.classfile.Unknown;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderVo extends Order {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productPicture;
}
