package cn.hhnail.backend.controller;

import cn.hhnail.backend.service.ElasticSearchService;
import io.swagger.annotations.Api;
import org.apache.http.HttpHost;
// import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vapi/es")
@Api(value = "ES控制器")
public class ElasticSearchController {

    private final static String HOTEL_INDEX_MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"name\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"address\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"price\": {\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"score\": {\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"brand\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"city\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"starName\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"business\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"latitude\": {\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"longitude\": {\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"pic\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"all\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);

    @Autowired
    ElasticSearchService esService;


    public static void main(String[] args) {
        // new ElasticSearchController().createIndex();
        // new ElasticSearchController().deleteIndex();
        new ElasticSearchController().existIndex();
    }


    @PostMapping("/createIndex")
    public List<Object> createIndex() {
        try {
            // 初始化连接
            HttpHost httpHost = HttpHost.create("http://192.168.225.130:9200");
            RestClientBuilder builder = RestClient.builder(httpHost);
            RestHighLevelClient esClient = new RestHighLevelClient(builder);
            // 索引库名称
            CreateIndexRequest req = new CreateIndexRequest("hotel");
            // CreateIndexRequest req = new CreateIndexRequest("hotel?include_type_name=false");
            // 索引库结构
            req.source(HOTEL_INDEX_MAPPING_TEMPLATE, XContentType.JSON);
            // 向服务器发送请求，创建索引库
            esClient.indices().create(req, RequestOptions.DEFAULT);


            // 关闭连接资源
            esClient.close();
            return null;
        } catch (IOException e) {
            // e.printStackTrace();
            logger.info("es get 报错：" + e);
            return null;
        }

    }

    @PostMapping("/deleteIndex")
    public List<Object> deleteIndex() {
        try {
            // 初始化连接
            RestHighLevelClient esClient = new RestHighLevelClient(
                    RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
            );
            // 索引库名称
            DeleteIndexRequest req = new DeleteIndexRequest("hotel");
            // 向服务器发送请求，创建索引库
            esClient.indices().delete(req, RequestOptions.DEFAULT);

            // 关闭连接资源
            esClient.close();
            return null;
        } catch (IOException e) {
            logger.info("ES删除失败，报错信息：" + e);
            return null;
        }

    }


    @PostMapping("/existIndex")
    public List<Object> existIndex() {
        try {
            // 初始化连接
            RestHighLevelClient esClient = new RestHighLevelClient(
                    RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
            );
            // 索引库名称
            GetIndexRequest req = new GetIndexRequest("hotel");
            // 向服务器发送请求，创建索引库
            boolean exists = esClient.indices().exists(req, RequestOptions.DEFAULT);
            System.out.println("hotel索引库是否存在" + exists);
            // 关闭连接资源
            esClient.close();
            return null;
        } catch (IOException e) {
            logger.info("ES查询失败，报错信息：" + e);
            return null;
        }

    }


}
