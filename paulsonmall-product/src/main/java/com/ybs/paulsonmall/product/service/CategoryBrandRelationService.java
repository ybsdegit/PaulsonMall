package com.ybs.paulsonmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybs.common.utils.PageUtils;
import com.ybs.paulsonmall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 01:09:31
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
