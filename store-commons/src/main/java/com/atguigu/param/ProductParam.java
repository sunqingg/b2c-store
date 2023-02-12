package com.atguigu.param;

import lombok.Data;
import org.apache.ibatis.javassist.SerialVersionUID;

import java.io.Serializable;

@Data
public class ProductParam implements Serializable {
    public static final Long SerialVersionUID = 1L;
//    public static final Long serialVersionUID = 1L;
    private Integer productId;
    private Integer productNum;
}
