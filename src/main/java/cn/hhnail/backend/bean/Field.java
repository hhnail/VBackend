package cn.hhnail.backend.bean;

import cn.hhnail.backend.enums.FieldType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
 * SQL字段
 */
public class Field {

    public Field(String fieldName) {
        this.fieldName = fieldName;
    }

    private String fieldName;

    private Object value;

    private FieldType fieldType;


}
