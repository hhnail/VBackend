package cn.hhnail.backend.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
public class FreeReportReqVO implements Serializable {

    private String reportName;
    private String moduleId ;
    private String description ;
    private String reportSql;
    private Map<String,Object> columnMap;


}
