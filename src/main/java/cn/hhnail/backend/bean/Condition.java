package cn.hhnail.backend.bean;

import cn.hhnail.backend.enums.CompareMethod;
import cn.hhnail.backend.enums.ConditionType;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL查询条件，即where、and、or的每一个条件
 */
@Data
@ToString
public class Condition {

    /**
     * 多值构造方法
     * @param conditionType
     * @param column
     * @param values
     * @param compareMethod
     */
    public Condition(
            ConditionType conditionType,
            String column,
            List<Object> values,
            CompareMethod compareMethod
    ) {
        this.conditionType = conditionType;
        this.column = column;
        this.values = values;
        this.compareMethod = compareMethod;
    }

    /**
     * 单值构造方法
     * @param conditionType
     * @param column
     * @param values
     * @param compareMethod
     */
    public Condition(
            ConditionType conditionType,
            String column,
            Object values,
            CompareMethod compareMethod
    ) {
        this.conditionType = conditionType;
        this.column = column;
        List<Object> _values = new ArrayList<>();
        _values.add(values);
        this.values = _values;
        this.compareMethod = compareMethod;
    }

    // 查询条件类型 and或or
    private ConditionType conditionType;
    // 字段
    private String column;
    // 值
    private List<Object> values;
    // 字段和值的比较方式
    private CompareMethod compareMethod;
}
