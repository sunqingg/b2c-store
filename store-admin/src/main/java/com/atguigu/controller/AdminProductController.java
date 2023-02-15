package com.atguigu.controller;

import com.atguigu.config.AliyunOSSUtils;
import com.atguigu.param.AdminProductParam;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.SearchProductParam;
import com.atguigu.pojo.Product;
import com.atguigu.service.AdminProductService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("product")
public class AdminProductController {

    @Autowired
    AliyunOSSUtils aliyunOSSUtils;

    @Autowired
    AdminProductService adminProductService;

    @GetMapping("list")
    public R list(SearchProductParam searchProductParam){
        return   adminProductService.list(searchProductParam);
    }

    @PostMapping("upload")
    public R upload(MultipartFile img) throws Exception {
        String filename = img.getOriginalFilename();
        String contentType = img.getContentType();
        long millis = System.currentTimeMillis();
        filename = filename + millis;
        String url = aliyunOSSUtils.uploadImage(filename, img.getBytes(), contentType, 1000);
        System.out.println("url = " + url);
        return R.ok("上传成功",url);


    }
    @PostMapping("save")
    public R save(ProductSaveParam productSaveParam){
        return   adminProductService.save(productSaveParam);
    }
    @PostMapping("update")
    public R update(Product product){
        return   adminProductService.update(product);
    }

    @PostMapping("remove")
    public R remove(Product product){
        return   adminProductService.remove(product);
    }


}
