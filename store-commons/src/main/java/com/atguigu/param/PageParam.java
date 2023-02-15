package com.atguigu.param;

import lombok.Data;

@Data
public class PageParam {
    private Integer currentPage = 1;
    private Integer pageSize = 15;
}
