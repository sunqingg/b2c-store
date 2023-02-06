package com.atguigu.param;

import lombok.Data;

import java.util.List;

@Data
public class ByCategoryParam {
    private List<Integer> categoryId;
    private Integer currentPage;
    private Integer pageSize;
}
