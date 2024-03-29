package cn.hhnail.backend.bean;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装查询条件
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
    // 字段列表
    private List<Field> fields = new ArrayList<>();
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


    /**
     * 添加查询条件
     *
     * @param condition
     */
    public void addCondition(Condition condition) {
        this.conditions.add(condition);
    }

    /**
     * 添加查询字段
     *
     * @param field
     */
    public void addField(Field field) {
        this.fields.add(field);
    }


    public List<String> getFieldsNameList() {
        List<String> names = new ArrayList<>();
        this.fields.forEach(item -> {
            names.add(item.getFieldName());
        });
        return names;
    }


}
