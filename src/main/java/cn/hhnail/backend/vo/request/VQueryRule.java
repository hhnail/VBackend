package cn.hhnail.backend.vo.request;

import cn.hhnail.backend.util.VStringUtil;
import cn.hhnail.backend.vo.response.AntdTableColumn;
import cn.hhnail.backend.vo.response.VCondition;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class VQueryRule {
    // 主表
    private String primaryTable;
    private String sql;
    // 查询字段规则
    private List<AntdTableColumn> queryColumns;
    // 查询数据规则
    private List<VCondition> conditions;

    public String getPrimaryTable() {
        if(VStringUtil.isEmpty(primaryTable)){
            String[] sqlFragment = this.sql.split(" ");
            for (int i = 1; i < sqlFragment.length; i++) {
                if ("from".equals(sqlFragment[i])) {
                    this.primaryTable = sqlFragment[i + 1];
                }
            }
        }
        return this.primaryTable;
    }
}
