package com.atguigu.vo;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartVo {
    private Integer id; //购物车id
    private Integer productId; //商品id
    private String productName;
    private String productImg;
    private Double price;
    private Integer num;
    private Integer maxNum;
    private Boolean check;

    public CartVo(Product product, Cart cart){
        this.id = cart.getId();
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productImg = product.getProductPicture();
        this.price = product.getProductPrice();
        this.num = cart.getNum();
        this.maxNum = product.getProductSales();
        this.check = false;
    }
}
