package cn.hhnail.backend.es;

import org.apache.http.HttpHost;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author Hhnail
 * @version 1.0
 * @description: 测试ES搜索文档的Java API
 * @date 2023/1/25 14:49
 */
public class EsSearchTest {

    private RestHighLevelClient elasticSearchClient;

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
     * 搜索全部
     *
     * @return
     */
    @Test
    public void matchAll() throws Exception {
        SearchRequest request = new SearchRequest("hotel");

        request.source()
                .query(QueryBuilders.matchAllQuery());

        SearchResponse response = elasticSearchClient
                .search(request, RequestOptions.DEFAULT);

        System.out.println("====================== 华丽的分割线 ====================== ");
        System.out.println(response);
        System.out.println("====================== 华丽的分割线 ====================== ");


    }

    /**
     * 解析查询结果
     *
     * @return
     */
    @Test
    public void analyseResult() throws Exception {
        SearchRequest request = new SearchRequest("hotel");

        request.source()
                .query(QueryBuilders.matchAllQuery());

        SearchResponse response = elasticSearchClient
                .search(request, RequestOptions.DEFAULT);

        // ctrl + alt + m 快速抽取方法
        handleResponse(response);

    }

    private void handleResponse(SearchResponse response) {
        System.out.println("====================== 华丽的分割线 ====================== ");
        SearchHits hits = response.getHits();
        final long totalCount = hits.getTotalHits().value;

        System.out.println("命中总条数:" + totalCount);

        final SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit : hitsHits) {
            final String json = hit.getSourceAsString();
            System.out.println(json);
        }
        System.out.println("====================== 华丽的分割线 ====================== ");
    }


    /**
     * match
     *
     * @return
     */
    @Test
    public void match() throws Exception {
        SearchRequest request = new SearchRequest("hotel");

        request.source()
                .query(QueryBuilders.matchQuery("all", "如家"));

        SearchResponse response = elasticSearchClient
                .search(request, RequestOptions.DEFAULT);

        handleResponse(response);


    }


    /**
     * term
     *
     * @return
     */
    @Test
    public void term() throws Exception {
        SearchRequest request = new SearchRequest("hotel");


        final TermQueryBuilder queryBuilder = QueryBuilders.termQuery("city", "上海");
        request.source()
                .query(queryBuilder);

        SearchResponse response = elasticSearchClient
                .search(request, RequestOptions.DEFAULT);

        handleResponse(response);


    }


    /**
     * range
     *
     * @return
     */
    @Test
    public void range() throws Exception {
        SearchRequest request = new SearchRequest("hotel");


        final RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("price").gte(100).lte(300);
        request.source()
                .query(queryBuilder);

        SearchResponse response = elasticSearchClient
                .search(request, RequestOptions.DEFAULT);

        handleResponse(response);


    }

    /**
     * bool
     *
     * @return
     */
    @Test
    public void bool() throws Exception {
        SearchRequest request = new SearchRequest("hotel");


        final BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.termQuery("city", "上海"));
        queryBuilder.filter(QueryBuilders.rangeQuery("price").lte(200));

        request.source().query(queryBuilder);

        SearchResponse response = elasticSearchClient
                .search(request, RequestOptions.DEFAULT);

        handleResponse(response);


    }

    /**
     * sort、from、size
     *
     * @return
     */
    @Test
    public void sortAndPage() throws Exception {

        int page = 2, size = 5;

        // 1-准备request
        SearchRequest request = new SearchRequest("hotel");

        // 2-准备查询条件
        request.source()
                .query(QueryBuilders.matchAllQuery())
                // 分页
                .from((page - 1) * size)
                .size(size)
                // 排序
                .sort("price", SortOrder.ASC)
        ;


        // 3-发送查询请求
        SearchResponse response = elasticSearchClient
                .search(request, RequestOptions.DEFAULT);

        // 4-解析打印查询结果
        handleResponse(response);


    }


    /**
     * highlight
     *
     * @return
     */
    @Test
    public void highlight() throws Exception {

        // 1-准备request
        SearchRequest request = new SearchRequest("hotel");

        // 2-准备查询条件
        request.source()
                .query(QueryBuilders.matchAllQuery())
                .highlighter(new HighlightBuilder()
                        .field("name")
                        .requireFieldMatch(false)
                )
        ;


        // 3-发送查询请求
        SearchResponse response = elasticSearchClient
                .search(request, RequestOptions.DEFAULT);

        // 4-解析打印查询结果
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField name = highlightFields.get("name");
            String highlightedName = name.getFragments()[0].string();
            // ... 处理高亮后的name字段
            System.out.println(highlightedName);
        }


    }


}
