package com.atguigu.param;

import lombok.Data;

@Data
public class PagePram {
    private Integer currentPage = 1 ;
    private Integer pageSize = 15;
}
