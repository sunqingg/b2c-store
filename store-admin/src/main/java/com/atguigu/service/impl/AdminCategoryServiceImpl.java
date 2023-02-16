package com.atguigu.service.impl;

import com.atguigu.clients.CategoryClient;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.service.AdminCategoryService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    CategoryClient categoryClient;

    @Cacheable(value = "list.category",key = "#param.currentPage+'-'+#param.pageSize")
    @Override
    public R list(PageParam param) {
        return categoryClient.adminList(param);
    }

    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.category",allEntries = true),
                    @CacheEvict(value = "category",key = "#category.categoryId()" )
            }
    )
    @Override
    public R save(Category category) {
        return categoryClient.adminSave(category);
    }

    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.category",allEntries = true),
                    @CacheEvict(value = "category",key = "#category.categoryId()" )
            }
    )
    @Override
    public R update(Category category) {
        return categoryClient.adminUpdate(category);
    }

    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.category",allEntries = true),
                    @CacheEvict(value = "category",key = "#category.categoryId()" )
            }
    )
    @Override
    public R remove(Category category) {
        return categoryClient.adminRemove(category);
    }
}
