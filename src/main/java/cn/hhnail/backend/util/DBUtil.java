package cn.hhnail.backend.util;

import cn.hhnail.backend.enums.CompareMethod;
import cn.hhnail.backend.enums.ConditionType;
import cn.hhnail.backend.vo.request.Condition;
import cn.hhnail.backend.vo.request.Field;
import cn.hhnail.backend.vo.request.QueryOption;

import java.sql.*;

/**
 * 数据库工具类
 */
public class DBUtil {

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
    public static void execute(QueryOption queryOption) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();
            StringBuffer sql = new StringBuffer("select")
                    .append(" ");
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
                    sql.append(StringUtils.groupConcat(item.getValues(), ", "));
                    sql.append(")");
                }
            });
            sql.append(";");
            // 执行查询
            ResultSet rs = stmt.executeQuery(sql.toString());

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
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
    }
}
