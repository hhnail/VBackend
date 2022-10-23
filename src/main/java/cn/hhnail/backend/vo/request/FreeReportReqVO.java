package cn.hhnail.backend.vo.request;

import cn.hhnail.backend.vo.response.AntdTableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
public class FreeReportReqVO implements Serializable {

    private String reportName; // 报表名称
    private Integer moduleId ; // 所属模块id
    private String description ; // 描述
    private String reportSql; // 报表SQL
    private String primaryTable; // 主表
    private List<AntdTableColumn> columnsView; // 显示字段

}
