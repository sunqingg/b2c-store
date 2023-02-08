package com.atguigu.vo;

import com.atguigu.pojo.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductVo extends Product {

}
