package com.atguigu.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.clients.CartClient;
import com.atguigu.clients.CategoryClient;
import com.atguigu.clients.CollectClient;
import com.atguigu.clients.OrderClient;
import com.atguigu.mapper.PictureMapper;
import com.atguigu.mapper.ProductMapper;
import com.atguigu.param.ByCategoryParam;
import com.atguigu.param.ProductParam;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.pojo.Category;
import com.atguigu.pojo.Picture;
import com.atguigu.pojo.Product;
import com.atguigu.service.ProductService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryClient categoryClient;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    CartClient cartClient;

    @Autowired
    OrderClient orderClient;

    @Autowired
    CollectClient collectClient;

    // redis中key的名字是lost.product::电视机(category.categoryName)
    @Cacheable(value = "list.product",key = "#category.categoryName")
    @Override
    public R promo(Category category) {
//        List<Category> data = (List<Category>) categoryClient.hots(category).getData();
//        System.out.println(data);
//        for (Category datum : data) {
//
//            System.out.println(datum.getCategoryId());
//        }
        R r = categoryClient.byName(category);
        if (r.getCode().equals(R.FAIL_CODE)){
            return  R.fail("promo调用的结果失败!");
        }
//        System.out.println(r.getData());
//        Category category1 = (Category) r.getData();
//        Integer categoryId = category1.getCategoryId();

        Category categoryObject = JSON.parseObject(JSON.toJSONString(r.getData()), Category.class);

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id",categoryObject.getCategoryId());
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> iPage = new Page<>(1,7);


        IPage<Product> productIPage = productMapper.selectPage(iPage, queryWrapper);


        return R.ok("数据查询成功",productIPage);
    }

    /**
     * 通过list<类型></>找到热销商品
     *
     * @param category
     * @return
     */
    @Override
    public R hots(Category category) {
        return null;
    }

    @Override
    public R clist() {
        return categoryClient.list();
    }

    @Override
    public R bycategory(ByCategoryParam category) {
//        if category.getCategoryId()
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        System.out.println(category);
        if (category.getCategoryId().size() > 0 && category.getCategoryId() != null) {
            queryWrapper.in("category_id", category.getCategoryId());
        }

        IPage<Product> iPage = new Page<>(category.getCurrentPage(),category.getPageSize());

        IPage<Product> iPage1 = productMapper.selectPage(iPage, queryWrapper);
        List<Product> records = iPage1.getRecords();
        Long aLong = productMapper.selectCount(queryWrapper);
        return R.ok(null,records,aLong);

    }

    /**
     * 更具商品ID查找商品详细信息
     *
     * @param product
     * @return
     */
    @Override
    public R detail(Product product) {
        System.out.println(product.getProductId());
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",product.getProductId());

        Product selectOne = productMapper.selectOne(queryWrapper);

        return R.ok(selectOne);
    }

    /**
     * 和detail差不多，他通过ID找到商品信息，这个返回是封装过得。
     *
     * @param product
     * @return
     */
    @Override
    public R pictures(Product product) {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",product.getProductId());

        Picture selectOne = pictureMapper.selectOne(queryWrapper);

        return R.ok(selectOne);

    }

    /**
     * 显示所有的商品
     *
     * @return
     */
    @Override
    public List<Product> list() {
        List<Product> products = productMapper.selectList(null);
        return products;

    }

    @Override
    public List<Product> ids(List<Integer> list) {
//        List<Product> productList = new ArrayList<>();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",list);

        List<Product> products = productMapper.selectList(queryWrapper);
        return products;
    }

    @Override
    public void batchNum(List<ProductParam> productParams) {
        log.info("++++++++++++" +productParams.toString());
        Map<Integer,ProductParam> productParamMap = productParams.stream()
                .collect(Collectors.toMap(ProductParam::getProductId,v -> v));
        Set<Integer> productIds = productParamMap.keySet();

        List<Product> productList = baseMapper.selectBatchIds(productIds);

        for (Product product : productList) {
            product.setProductNum(product.getProductNum() - productParamMap.get(product.getProductId()).getProductNum());

            product.setProductSales(product.getProductSales() + productParamMap.get(product.getProductId()).getProductNum());

        }
        this.updateBatchById(productList);

    }

    @Override
    public Product id(Integer integer) {
        QueryWrapper<Product> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("product_id",integer);

        Product product = productMapper.selectOne(queryWrapper);
        return product;
    }

    @Override
    public R save(ProductSaveParam productSaveParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productSaveParam, product);
        String pictures = productSaveParam.getPicture();

        if (!StringUtils.isEmpty(pictures)) {
            String[] pics = pictures.split("\\+");
            for (String pic : pics) {
                Picture picture = new Picture();
                picture.setIntro(null);
                picture.setProductId(product.getProductId());
                picture.setProductPicture(pic);
                pictureMapper.insert(picture);
            }

        }
        int rows = productMapper.insert(product);

        if (rows == 0){
            return R.fail("商品保存失败");
        }
        rabbitTemplate.convertAndSend("topic.ex","insert.product",product);
        return R.ok("商品数据保存成功");

    }

    @Override
    public R update(Product product) {
        int rows = productMapper.updateById(product);

        if (rows == 0){
            return R.fail("商品数据更新失败");
        }

        rabbitTemplate.convertAndSend("topic.ex","insert.product",product);
        return R.ok("商品数据更新成功");
    }

    @Override
    public R remove(Product product) {
        R check = cartClient.check(product.getProductId());
        if (check.getCode().equals("004")) {
            return check;
        }

        R check1 = orderClient.check(product.getProductId());
        if (check1.getCode().equals("004")){
            return check1;
        }

//        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("")
        pictureMapper.deleteById(product);

        collectClient.removeByPid(product.getProductId());

        int rows = productMapper.deleteById(product);

        if (rows == 0 ){
            return R.fail("删除商品失败");
        }

        rabbitTemplate.convertAndSend("topic.ex","delete.product",product.getProductId());

        try{
            Thread.sleep(500);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok("商品删除成功");
    }
}




















