package com.ybs.paulsonmall.search.controller;

import com.ybs.common.exception.BizCodeEnume;
import com.ybs.common.to.es.SkuEsModel;
import com.ybs.common.utils.R;
import com.ybs.paulsonmall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: ElasticSearchSaveController
 * @Author Paulson
 * @Date 2020/7/19
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/search/save")
public class ElasticSearchSaveController {

    @Autowired
    ProductSaveService productSaveService;

    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {

        boolean b = false;
        try {
            b = productSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            log.error("ElasticSearchSaveController 商品上架错误:[{}]", e);
            return R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if (!b) {
            return R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }
        return R.ok();
    }

    @PostMapping("/test")
    public R test() {
        return R.ok();
    }
}