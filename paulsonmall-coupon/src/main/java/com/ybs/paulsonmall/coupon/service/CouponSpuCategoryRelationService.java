package com.ybs.paulsonmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybs.common.utils.PageUtils;
import com.ybs.paulsonmall.coupon.entity.CouponSpuCategoryRelationEntity;

import java.util.Map;

/**
 * 优惠券分类关联
 *
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 12:55:45
 */
public interface CouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

