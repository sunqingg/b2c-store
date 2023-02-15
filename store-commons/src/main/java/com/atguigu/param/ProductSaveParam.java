package com.atguigu.param;

import com.atguigu.pojo.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductSaveParam extends Product implements Serializable {
    private String pictures;
}
