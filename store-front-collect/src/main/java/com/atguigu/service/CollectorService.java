package com.atguigu.service;

import com.atguigu.pojo.Collect;
import com.atguigu.utils.R;

public interface CollectorService {

    R save(Collect collect);

    R list(Collect collect);

    R remove(Collect collect);

    Object removeByPid(Integer productId);
}
