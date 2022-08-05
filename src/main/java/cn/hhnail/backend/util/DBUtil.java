package cn.hhnail.backend.util;

import cn.hhnail.backend.controller.ComController;
import cn.hhnail.backend.enums.CompareMethod;
import cn.hhnail.backend.enums.ConditionType;
import cn.hhnail.backend.bean.Condition;
import cn.hhnail.backend.bean.Field;
import cn.hhnail.backend.bean.QueryOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库工具类
 */
public class DBUtil {

    // Logger logger = LoggerFactory.getLogger(DBUtil.class);

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/hhnail";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    public static void main(String[] args) {
        QueryOption queryOption = new QueryOption();
        queryOption.setPrimaryTable("sys_table");
        queryOption.addField(new Field("id"));
        queryOption.addField(new Field("name"));
        queryOption.addField(new Field("label"));
        queryOption.addCondition(new Condition(ConditionType.AND, "deleted", 0, CompareMethod.EQUAL));
        execute(queryOption);
    }

    /**
     * 执行
     */
    public static Map<String, Object> execute(QueryOption queryOption) {

        Map<String, Object> result = new HashMap<>();
        StringBuffer sql = new StringBuffer("select")
                .append(" ");

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();
            // 拼接查询字段
            sql.append(StringUtils.groupConcat(queryOption.getFieldsNameList(), ", "))
                    .append(" ");
            // 拼接查询表格
            sql.append("from ")
                    .append(queryOption.getPrimaryTable())
                    .append(" ");
            // 拼接查询条件
            sql.append("where 1=1").append(" ");
            queryOption.getConditions().forEach(item -> {
                sql.append(item.getConditionType()).append(" ");
                sql.append(item.getColumn()).append(" ");
                sql.append(item.getCompareMethod().getValue()).append(" ");
                if (item.getValues().size() < 2) {
                    sql.append(item.getValues().get(0).toString()).append(" ");
                } else {
                    sql.append("in (");
                    sql.append(StringUtils.groupConcat(StringUtils.objectList2String(item.getValues()), ", "));
                    sql.append(")");
                }
            });
            sql.append(";");
            // 执行查询
            System.out.println(String.format("execute by queryoption final sql:【%s】", sql));
            ResultSet rs = stmt.executeQuery(sql.toString());

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                for (int i = 0; i < queryOption.getFieldsNameList().size(); i++) {
                    String fieldName = queryOption.getFieldsNameList().get(i);
                    result.put(fieldName, rs.getString(fieldName));
                }
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
            return result;
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }

        // 报错统一返回结果
        result.put("message", String.format("execute by queryoption final sql:【%s】", sql));
        return result;
    }
}
