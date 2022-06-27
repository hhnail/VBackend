package cn.hhnail.backend.vo.request;

import lombok.Data;
import lombok.ToString;
import sun.java2d.pipe.SpanClipRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件
 */
@Data
@ToString
public class QueryOption {

    // 查询的主表
    private String primaryTable;
    // 模块编号
    private Integer moduleId;
    // 条件列表
    private List<Condition> conditions = new ArrayList<>();
    // 是否分页
    private Boolean paged;
    // 分页——每页大小
    private Integer pageSize;
    // 分页——当前页索引
    private Integer pageIndex;
    // 排序字段
    private List<String> orderBy;
    //
    private Integer limit;

}
