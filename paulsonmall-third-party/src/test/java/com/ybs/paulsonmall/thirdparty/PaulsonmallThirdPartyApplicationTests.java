package com.ybs.paulsonmall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;

@SpringBootTest
class PaulsonmallThirdPartyApplicationTests {

    @Autowired
    private OSSClient ossClient;

    @Test
    void testUpload1() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("D:\\B01.jpeg");
        ossClient.putObject("paulsonmall", "B02.jpeg", inputStream);
        System.out.println("上传完成");
    }

    @Test
    void contextLoads() {
        System.out.println(LocalDate.now().toString());

    }

}
