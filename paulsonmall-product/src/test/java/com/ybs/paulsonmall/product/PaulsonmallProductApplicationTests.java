package com.ybs.paulsonmall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybs.paulsonmall.product.entity.BrandEntity;
import com.ybs.paulsonmall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class PaulsonmallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    // @Autowired
    // private OSSClient ossClient;
    
    // @Test
    // void testUpload1() throws FileNotFoundException {
    //     InputStream inputStream = new FileInputStream("D:\\B01.jpeg");
    //     ossClient.putObject("paulsonmall", "B01.jpeg", inputStream);
    //     System.out.println("上传完成");
    // }

    @Test
    void testUpload() throws FileNotFoundException {
        // String endpoint = "oss-cn-beijing.aliyuncs.com";
        // String accessKeyId = "LTAI4G9RMM8hou2KsAPGraBR";
        // String accessKeySecret = "vD6l6jX0i7MLBilM1X67yMBgU8dVfN";
        // OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // InputStream inputStream = new FileInputStream("D:\\B01.jpeg");
        // ossClient.putObject("paulsonmall", "B01.jpeg", inputStream);
        // ossClient.shutdown();
        // System.out.println("上传完成");
    }

    // @Test
    void test() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("爱国");
        brandEntity.setName("华为");
        brandService.save(brandEntity);
        System.out.println("保存成功");
    }

    @Test
    void test1() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("华为是一家伟大的公司");
        brandService.updateById(brandEntity);
        System.out.println("更新成功");
    }

    @Test
    void test2() {
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        list.forEach(item -> {
            System.out.println(item);
        });
    }


}
