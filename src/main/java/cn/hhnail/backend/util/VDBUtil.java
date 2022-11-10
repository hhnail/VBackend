package cn.hhnail.backend.util;

import cn.hhnail.backend.enums.SqlTypeEnum;

import java.util.List;
import java.util.Map;

public class VDBUtil {

    private String tableName;
    private SqlTypeEnum sqlType;
    private Map<String, Object> updateColumns;
    private Map<String, Object> where;

    public VDBUtil() {

    }

    public VDBUtil tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public VDBUtil sqlType(String sqlType) {
        // this.sqlType = sqlType;
        return this;
    }

    public VDBUtil sqlType(SqlTypeEnum sqlType) {
        this.sqlType = sqlType;
        return this;
    }

    public VDBUtil addUpdateColumn(String column, Object value) {
        this.updateColumns.put(column, value);
        return this;
    }

    public VDBUtil addWhere(String column, Object value) {
        this.where.put(column, value);
        return this;
    }


    public VDBUtil build(Map<String, Object> param) {
        String tableName = param.get("tableName").toString();
        String sqlType = param.get("sqlType").toString();
        List<Map<String, Object>> updateColumns = (List<Map<String, Object>>) param.get("update_columns");
        List<Map<String, Object>> where = (List<Map<String, Object>>) param.get("where");

        VDBUtil DBUtil = new VDBUtil()
                .tableName(tableName)
                .sqlType(sqlType);

        updateColumns.forEach(item -> {
            DBUtil.addUpdateColumn(item.get("column").toString(), item.get("value"));
        });

        where.forEach(item -> {
            DBUtil.addWhere(item.get("column").toString(), item.get("value"));
        });


        return this;
    }


}
