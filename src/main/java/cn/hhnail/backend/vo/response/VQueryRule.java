package cn.hhnail.backend.vo.response;

import cn.hhnail.backend.vo.response.VColumnRule;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class VQueryRule {
    // 主表
    private String primaryTable;
    // 查询字段规则
    private List<VColumnRule> queryColumns;
    // 查询数据规则
    private List<VConditionRule> conditions;

}
