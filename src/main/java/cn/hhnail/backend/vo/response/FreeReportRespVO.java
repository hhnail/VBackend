package cn.hhnail.backend.vo.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 自由报表——响应VO
 */
@Data
@ToString
public class FreeReportRespVO implements Serializable {

    private String reportName;

    private String moduleId;

    private String description;

    private String reportSql;

    private List<AntdTableColumn> viewColumns;

    // private List<AntdTableData> viewData;

}
