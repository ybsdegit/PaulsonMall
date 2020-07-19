package com.ybs.paulsonmall.product.feign;

import com.ybs.common.to.es.SkuEsModel;
import com.ybs.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName: SearchFeignService
 * @Author Paulson
 * @Date 2020/7/19
 * @Description:
 */
@FeignClient("paulsonmall-search")
public interface SearchFeignService {

    @PostMapping("/search/save/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);

    @PostMapping("/search/save/test")
    public R test();

}
