package com.ybs.paulsonmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybs.common.utils.PageUtils;
import com.ybs.paulsonmall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 01:09:31
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);

    List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId);

    void saveProductAttr(List<ProductAttrValueEntity> collect);
}

