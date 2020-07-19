package com.ybs.paulsonmall.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;

/**
 * @ClassName: PaulsonTest
 * @Author Paulson
 * @Date 2020/7/19
 * @Description:
 */
public class PaulsonTest {

    @Test
    void test() {
        String s = "[{skuId=35, hasStock=true}, {skuId=37, hasStock=false}, {skuId=40, hasStock=false}, {skuId=44, hasStock=false}]";
        JSONArray jsonObject = JSON.parseObject(s, JSONArray.class);
        System.out.println(JSON.toJSONString(jsonObject));
    }
}
