package com.atguigu.controller;

import com.atguigu.pojo.Collect;
import com.atguigu.service.CollectorService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collect")
public class CollectorController {
    @Autowired
    CollectorService collectorService;


    @PostMapping("save")
    public R save(@RequestBody @Validated Collect collect) {
        return collectorService.save(collect);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated Collect collect) {
        return collectorService.list(collect);
    }
    @PostMapping("remove")
    public R remove(@RequestBody @Validated Collect collect) {
        return collectorService.remove(collect);
    }

    @PostMapping("removeByPid")
    public Object removeByPid(@RequestBody Integer productId){
        return collectorService.removeByPid(productId);
    }
}
