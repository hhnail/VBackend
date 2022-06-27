package cn.hhnail.backend.vo.request;

import cn.hhnail.backend.enums.CompareMethod;
import cn.hhnail.backend.enums.ConditionType;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * SQL查询条件，即where、and、or的每一个条件
 */
@Data
@ToString
public class Condition {

    // 查询条件类型 and或or
    private ConditionType type;
    // 字段
    private String column;
    // 值
    private List<Object> values;
    // 字段和值的比较方式
    private CompareMethod compareMethod;
}
