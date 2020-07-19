package com.ybs.paulsonmall.search.service;

import com.ybs.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: ProductSaveService
 * @Author Paulson
 * @Date 2020/7/19
 * @Description:
 */
public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
