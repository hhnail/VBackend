package cn.hhnail.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "pattern") // pattern.xxx开头的配置就会被热部署
public class PatternProperties {

    private String dateFormate;


}
