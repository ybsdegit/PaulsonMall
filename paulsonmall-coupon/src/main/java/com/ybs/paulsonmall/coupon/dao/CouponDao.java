package com.ybs.paulsonmall.coupon.dao;

import com.ybs.paulsonmall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 12:55:45
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
