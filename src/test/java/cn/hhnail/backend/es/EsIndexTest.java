package cn.hhnail.backend.es;

import cn.hhnail.backend.bean.Hotel;
import cn.hhnail.backend.bean.HotelDTO;
import cn.hhnail.backend.service.HotelService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Hhnail
 * @version 1.0
 * @description: 测试ES搜索文档的Java API
 * @date 2023/1/25 14:49
 */
public class EsIndexTest {

    private RestHighLevelClient elasticSearchClient;

    @Autowired
    HotelService hotelService;

    @Before
    public void setUp() {
        this.elasticSearchClient = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
        );
    }


    @After
    public void tearDown() throws Exception {
        this.elasticSearchClient.close();
    }

    /**
     * 批量插入文档
     *
     * @return
     */
    @Test
    public void batchAddDocument() throws Exception {
        // 1-初始化连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
        );
        // 从数据库查询po
        List<Hotel> hotelPoList = hotelService.getHotelList();
        List<HotelDTO> hotelDTOList = new ArrayList<>();
        for (Hotel hotelPO : hotelPoList) {
            HotelDTO hotelDTO = new HotelDTO(hotelPO);
            hotelDTOList.add(hotelDTO);
        }
        // 2-准备文档的请求对象
        BulkRequest request = new BulkRequest();
        for (HotelDTO hotelDTO : hotelDTOList) {
            request.add(
                    new IndexRequest("hotel")
                            .id(hotelDTO.getId().toString())
                            .source(
                                    JSONObject.toJSONString(hotelDTO),
                                    XContentType.JSON
                            )
            );
        }
        esClient.bulk(request, RequestOptions.DEFAULT);
    }
}
