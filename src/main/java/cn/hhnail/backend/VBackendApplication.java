package cn.hhnail.backend;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("cn.hhnail.backend.mapper")
public class VBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(VBackendApplication.class, args);

    }

	// ES客户端自动注入，交由spring管理
    @Bean
    public RestHighLevelClient client() {
        return new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.225.130:9200"))
        );
    }


}
