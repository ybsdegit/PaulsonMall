package com.ybs.paulsonmall.order.dao;

import com.ybs.paulsonmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 16:53:12
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
