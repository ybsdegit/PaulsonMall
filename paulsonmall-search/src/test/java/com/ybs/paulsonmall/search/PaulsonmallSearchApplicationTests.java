package com.ybs.paulsonmall.search;

import com.alibaba.fastjson.JSON;
import com.ybs.paulsonmall.search.config.ElasticSearchConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PaulsonmallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        System.out.println(client);
    }

    /**
     * 存储数据
     */
    @Test
    void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        // indexRequest.source("username", "paulson", "age", 18, "gender", "男");
        User user = new User();
        // User user = new User("paulson", "男", 18);
        user.setUsername("Paulson");
        user.setGender("man");
        user.setAge(19);

        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);
        IndexResponse index = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(index);
    }

    @Test
    void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bank");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));

        // searchSourceBuilder.from();
        // searchSourceBuilder.size();
        // searchSourceBuilder.aggregation();

        searchSourceBuilder.aggregation(AggregationBuilders.terms("ageAgg").field("age").size(10));
        searchSourceBuilder.aggregation(AggregationBuilders.avg("balanceAng").field("balance"));

        System.out.println(searchSourceBuilder.toString());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(searchResponse.toString());

        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            String sourceAsString = hit.getSourceAsString();
            Account account = JSON.parseObject(sourceAsString, Account.class);
            System.out.println(account.toString());
        }

        Aggregations aggregations = searchResponse.getAggregations();
        // for (Aggregation aggregation : aggregations.asList()) {
        //     System.out.println(aggregation.getName());
        //     System.out.println(aggregation.getMetaData());
        //     System.out.println(aggregation.getType());
        // }
        Terms ageAgg = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg.getBuckets()) {
            System.out.println("平均年龄" + bucket.getKeyAsString());
        }
        // Avg balanceAvg = aggregations.get("balanceAvg");
        // System.out.println("平均薪资" + balanceAvg.getValue());

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static
    class User {
        private String username;
        private String gender;
        private Integer age;
    }

    @Data
    static
    class Account {
        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;

    }


}
