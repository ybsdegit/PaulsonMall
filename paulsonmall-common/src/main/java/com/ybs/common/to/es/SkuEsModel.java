package com.ybs.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: SkuEsModel
 * @Author Paulson
 * @Date 2020/7/19
 * @Description:
 */

@Data
public class SkuEsModel {

    /**
     * "skuId":{
     * "type":"long"
     * },
     * "spuId":{
     * "type":"keyword"
     * },
     * "skuTitle":{
     * "type":"text",
     * "analyzer":"ik_smart"
     * },
     * "skuPrice":{
     * "type":"keyword"
     * },
     * "skuImg":{
     * "type":"keyword",
     * "index":false,
     * "doc_values":false
     * },
     * <p>
     * <p>
     * "saleCount":{
     * "type":"long"
     * },
     * "hasStock":{
     * "type":"boolean"
     * },
     * "hotScore":{
     * "type":"long"
     * },
     * "brandId":{
     * "type":"long"
     * },
     * "catalogId":{
     * "type":"long"
     * },
     * <p>
     * <p>
     * "brandName":{
     * "type":"keyword",
     * "index":false,
     * "doc_values":false
     * },
     * "brandImg":{
     * "type":"keyword",
     * "index":false,
     * "doc_values":false
     * },
     * "catalogName":{
     * "type":"keyword",
     * "index":false,
     * "doc_values":false
     * },
     * <p>
     * "attrs":{
     * "type":"nested",
     * "properties":{
     * "attrId":{
     * "type":"long"
     * },
     * "attrName":{
     * "type":"keyword",
     * "index":false,
     * "doc_values":false
     * },
     * "attrValue":{
     * "type":"keyword"
     * }
     * }
     * }
     */
    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Long saleCount;
    private Boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catalogName;
    private List<Attrs> attrs;


    @Data
    public static class Attrs {
        private Long attrId;
        private String attrName;
        private String attrValue;
    }

}
