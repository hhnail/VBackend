package cn.hhnail.backend.util;

import cn.hhnail.backend.controller.ComController;
import cn.hhnail.backend.enums.CompareMethod;
import cn.hhnail.backend.enums.ConditionType;
import cn.hhnail.backend.bean.Condition;
import cn.hhnail.backend.bean.Field;
import cn.hhnail.backend.bean.QueryOption;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库工具类
 */
@Component
@Slf4j
@Data
public class DBUtil {

    // @Value("${spring.datasource.driver-class-name}")
    // String JDBC_DRIVER;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/hhnail";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    private Connection conn = null;
    private Statement stmt = null;

    public static void main(String[] args) {
        // QueryOption queryOption = new QueryOption();
        // queryOption.setPrimaryTable("sys_table");
        // queryOption.addField(new Field("id"));
        // queryOption.addField(new Field("name"));
        // queryOption.addField(new Field("label"));
        // queryOption.addCondition(new Condition(ConditionType.AND, "deleted", 0, CompareMethod.EQUAL));
        //
        // DBUtil db = new DBUtil();
        // List<Map<String, Object>> execute = db.execute(queryOption);
        // System.out.println(execute);
    }


    /**
     * 获取数据库连接
     */
    public Connection getConnection() throws Exception {
        if (conn != null) {
            return conn;
        }
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    /**
     * 关闭数据库连接
     */
    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭SQL执行对象
     */
    private void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行查询
     * 需要返回结果集
     */
    public List<Map<String, Object>> execute(QueryOption queryOption) {

        List<Map<String, Object>> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer();

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            // 拼接查询字段
            sql.append("select ");
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
            // TODO order by、limit、page...
            sql.append(";");
            // 执行查询
            System.out.println(String.format("execute by queryoption final sql:【%s】", sql));
            ResultSet rs = stmt.executeQuery(sql.toString());

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < queryOption.getFieldsNameList().size(); i++) {
                    String fieldName = queryOption.getFieldsNameList().get(i);
                    map.put(fieldName, rs.getString(fieldName));
                }
                result.add(map);
            }
            // 完成后关闭
            rs.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeConnection(conn);
            closeStatement(stmt);
        }

        // 报错统一返回结果
        Map<String, Object> errorMsg = new HashMap<>();
        errorMsg.put("msg", "error");
        errorMsg.put("sql", sql);
        result.add(errorMsg);
        return result;
    }

    /**
     * 执行insert、delete、update
     * 无需返回结果集
     */
    public void executeNoResult(String sql) {


        try {
            conn = getConnection();
            stmt = conn.createStatement();
            // 执行
            System.out.println(String.format("execute final sql:【%s】", sql));
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeConnection(conn);
            closeStatement(stmt);
        }
    }
}
