package com.atguigu.service.impl;

import com.atguigu.clients.ProductClient;
import com.atguigu.clients.SearchClient;
import com.atguigu.param.AdminProductParam;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.SearchProductParam;
import com.atguigu.pojo.Product;
import com.atguigu.service.AdminProductService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class AdminProductServiceImpl implements AdminProductService {

    @Autowired
    SearchClient searchClient;

    @Autowired
    ProductClient productClient;

    @Cacheable(value ="list.product",key =
            "#searchProductParam.currentPage+'-'+#searchProductParam.pageSize")
    @Override
    public R list(SearchProductParam searchProductParam) {
        return searchClient.searchProduct(searchProductParam);
    }

    @Caching(
            evict = {
                    @CacheEvict(value ="list.product",allEntries = true)
            }
    )
    @Override
    public R save(ProductSaveParam productSaveParam) {
        return productClient.save(productSaveParam);
    }
    @Caching(
            evict = {
                    @CacheEvict(value ="list.product",allEntries = true)
            }
    )
    @Override
    public R update(Product product) {
        return productClient.update(product);

    }

    @Override
    public R upload(AdminProductParam adminProductParam) {
        return null;
    }
    @Caching(
            evict = {
                    @CacheEvict(value ="list.product",allEntries = true)
            }
    )
    @Override
    public R remove(Product product) {
        return productClient.remove(product);
    }

}
