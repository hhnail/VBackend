package cn.hhnail.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SqlTypeEnum {

    INSERT("0"),
    SELECT("1"),
    UPDATE("2"),
    DELETE("3"),
    ;

    private String code;


    public SqlTypeEnum getInstance(String code){
        SqlTypeEnum[] values = SqlTypeEnum.values();
        return null;
    }


}
