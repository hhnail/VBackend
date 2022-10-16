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

    // 报表名称
    private String reportName;
    // 所属模块id
    private Integer moduleId ;
    // 描述
    private String description ;
    // 报表SQL
    private String reportSql;
    // 显示字段
    private List<AntdTableColumn> columnsView;


}
