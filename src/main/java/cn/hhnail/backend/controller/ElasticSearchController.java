package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.Hotel;
import cn.hhnail.backend.bean.HotelDTO;
import cn.hhnail.backend.service.ElasticSearchService;
import cn.hhnail.backend.service.HotelService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggester;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            "      \"location\":{\n" +
            "        \"type\": \"text\"\n" +
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
    @Autowired
    RestHighLevelClient esClient;
    @Autowired
    HotelService hotelService;


    public static void main(String[] args) {
        // new ElasticSearchController().createIndex();
        // new ElasticSearchController().deleteIndex();
        // new ElasticSearchController().existIndex();


    }


    /**
     * 创建索引库
     *
     * @return
     */
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


    /**
     * 删除索引库
     *
     * @return
     */
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


    /**
     * 判断索引库是否存在
     *
     * @return
     */
    @PostMapping("/existIndex")
    public List<Object> existIndex() {
        try {
            // 初始化连接
            // RestHighLevelClient esClient = new RestHighLevelClient(
            //         RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
            // );
            // 索引库名称
            GetIndexRequest req = new GetIndexRequest("hotel");
            // 向服务器发送请求，创建索引库
            boolean exists = esClient.indices().exists(req, RequestOptions.DEFAULT);
            System.out.println("hotel索引库是否存在" + exists);
            // 关闭连接资源
            // esClient.close();
            return null;
        } catch (IOException e) {
            logger.info("ES查询失败，报错信息：" + e);
            return null;
        }

    }


    /**
     * 新增（全量更新）文档
     *
     * @return
     */
    @PostMapping("/addDocument")
    public Object addDocument(@RequestBody Map<String, Object> param) throws Exception {
        // 1-初始化连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
        );
        // 从数据库查询po
        Long id = 36934L;
        Hotel hotelPo = hotelService.getHotelById(id);
        HotelDTO hotelDTO = new HotelDTO(hotelPo);
        // 2-准备文档的请求对象
        IndexRequest request = new IndexRequest("hotel").id(hotelDTO.getId().toString());
        request.source(JSONObject.toJSONString(hotelDTO), XContentType.JSON);
        esClient.index(request, RequestOptions.DEFAULT);
        return null;
    }


    /**
     * 查询文档
     *
     * @return
     */
    @PostMapping("/getDocument")
    public void getDocument(@RequestBody Map<String, Object> param) throws Exception {
        // 1-初始化连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
        );

        GetRequest request = new GetRequest("hotel", "36934");
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);
        String sourceString = response.getSourceAsString();
        System.out.println(JSONObject.parse(sourceString));
    }


    /**
     * 局部更新文档
     *
     * @return
     */
    @PostMapping("/updateDocument")
    public void updateDocument(@RequestBody Map<String, Object> param) throws Exception {
        // 1-初始化连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
        );

        UpdateRequest request = new UpdateRequest("hotel", "36934");
        request.doc(
                "price", 888,
                "starName", "三钻"
        );
        esClient.update(request, RequestOptions.DEFAULT);

    }

    /**
     * 删除文档
     *
     * @return
     */
    @PostMapping("/deleteDocument")
    public void deleteDocument(@RequestBody Map<String, Object> param) throws Exception {
        // 1-初始化连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
        );
        DeleteRequest request = new DeleteRequest("hotel", "36934");
        esClient.delete(request, RequestOptions.DEFAULT);
    }


    /**
     * 批量插入文档
     *
     * @return
     */
    @PostMapping("/batchAddDocument")
    public void batchAddDocument(@RequestBody Map<String, Object> param) throws Exception {
        // 1-初始化连接
        // RestHighLevelClient esClient = new RestHighLevelClient(
        //         RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
        // );
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


    /**
     * 自动补全提示
     *
     * @return
     */
    @PostMapping("/autoComplete")
    public List<String> autoComplete(@RequestParam("prefix") String prefix) throws Exception {

        List<String> responseSuggestions = new ArrayList<>();

        // 1-准备request对象
        SearchRequest request = new SearchRequest("hotel");
        // 2-准备DSL
        request.source()
                .suggest(new SuggestBuilder().addSuggestion(
                        "suggestions", // suggest map的key
                        SuggestBuilders
                                .completionSuggestion("suggestions") // completion的field
                                .prefix(prefix)
                                .skipDuplicates(true)
                                .size(10)

                ));
        // 3-发送请求
        final SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        // 4-解析es的响应结果
        final Suggest suggest = response.getSuggest();

        CompletionSuggestion suggestions = suggest.getSuggestion("suggestions");


        for (CompletionSuggestion.Entry.Option option : suggestions.getOptions()) {
            responseSuggestions.add(option.getText().string());
        }
        return responseSuggestions;

    }

}
