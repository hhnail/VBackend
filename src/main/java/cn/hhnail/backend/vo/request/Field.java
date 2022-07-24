package cn.hhnail.backend.vo.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Field {

    public Field(String fieldName) {
        this.fieldName = fieldName;
    }

    private String fieldName;

    private Object value;


}
