package com.ybs.paulsonmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybs.common.utils.PageUtils;
import com.ybs.common.utils.Query;
import com.ybs.paulsonmall.product.dao.BrandDao;
import com.ybs.paulsonmall.product.entity.BrandEntity;
import com.ybs.paulsonmall.product.service.BrandService;
import com.ybs.paulsonmall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override

    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(key)) {
            wrapper.eq("brand_id", key).or().like("name", key);
        }
        return new PageUtils(this.page(new Query<BrandEntity>().getPage(params), wrapper));
    }

    @Transactional
    @Override
    public void updateDetail(BrandEntity brand) {
        // 冗余字段
        this.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())) {
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            // geng
        }
    }

}