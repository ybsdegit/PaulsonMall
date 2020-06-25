package com.ybs.paulsonmall.product.dao;

import com.ybs.paulsonmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Paulson
 * @email ybsdeyx@foxmail.com
 * @date 2020-06-25 01:09:30
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
