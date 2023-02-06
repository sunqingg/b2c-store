package com.atguigu.service.impl;

import com.atguigu.param.SearchProductParam;
import com.atguigu.pojo.Product;
import com.atguigu.service.SearchSearvice;
import com.atguigu.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.collect.HppcMaps;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearhServiceImpl implements SearchSearvice {
    /**
     * 通过关键字搜索商品
     *
     * @return
     */
    @Autowired
    RestHighLevelClient client;

    @Override
    public R searchProduct(SearchProductParam searchProductParam) {
        String search = searchProductParam.getSearch();
        SearchRequest searchRequest = new SearchRequest("product");

        if (search == null){
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else {
            searchRequest.source().query(QueryBuilders.matchQuery("all",search));
        }
        searchRequest.source().from(searchProductParam.getCurrentPage());
        searchRequest.source().size(searchProductParam.getPageSize());

        SearchResponse response = null;

        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        SearchHit[] searchHits = hits.getHits();

        List<Product> products = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (SearchHit searchHit : searchHits) {
            String sourceAsString = searchHit.getSourceAsString();
            try {
                Product product = objectMapper.readValue(sourceAsString,Product.class);
                products.add(product);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return R.ok(null,products,total);

    }
}
