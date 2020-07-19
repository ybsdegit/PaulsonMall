package com.ybs.paulsonmall.ware.vo;

import lombok.Data;

/**
 * @ClassName: SkuHasStockVo
 * @Author Paulson
 * @Date 2020/7/19
 * @Description:
 */
@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
}
