package com.atguigu.service;

import com.atguigu.param.ByCategoryParam;
import com.atguigu.param.ProductParam;
import com.atguigu.pojo.Category;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

import java.util.List;

public interface ProductService extends IService<Product> {

    /**
     * 通过类型找商品
     * @param category
     * @return
     */
    R promo(Category category);

    /**
     * 通过list<类型></>找到热销商品
     * @param category
     * @return
     */
    R hots(Category category);

    R clist();

    R bycategory(ByCategoryParam byCategoryParam);

    /**
     * 更具商品ID查找商品详细信息
     * @param product
     * @return
     */
    R detail(Product product);

    /**
     * 和detail差不多，他通过ID找到商品信息，这个返回是封装过得。
     * @param product
     * @return
     */
    R pictures(Product product);

    /**
     * 显示所有的商品
     * @return
     */
    List<Product> list();

    List<Product> ids(List<Integer> list);

    void batchNum(List<ProductParam> productParams);

    Product id(Integer integer);
}
