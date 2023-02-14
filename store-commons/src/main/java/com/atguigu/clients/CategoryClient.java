package com.atguigu.clients;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("category-service")
public interface CategoryClient {

    @PostMapping("/category/hots")
    R hots(Category category);

    @PostMapping("/category/hots")
    R byName(Category category);
    @GetMapping("/category/list")
    R list();

    @GetMapping("/category/alist")
    public List<Category> alist();

    @GetMapping("/category/adminList")
    public R adminList(PageParam pageParam);

    @PostMapping("/category/adminSave")
    public R adminSave(@RequestBody Category category);
    @PostMapping("/category/adminUpdate")
    public R adminUpdate(Category category);
    @PostMapping("/category/adminRemove")
    public R adminRemove(Category category);
}
