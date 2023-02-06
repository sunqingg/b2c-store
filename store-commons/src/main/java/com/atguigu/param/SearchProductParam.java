package com.atguigu.param;

import lombok.Data;

@Data
public class SearchProductParam {

    String search;

    Integer currentPage;

    Integer pageSize;
}
