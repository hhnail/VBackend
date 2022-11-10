package cn.hhnail.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Deprecated
public enum SqlTypeEnum {

    INSERT("0"),
    SELECT("1"),
    UPDATE("2"),
    DELETE("3"),
    ;

    private String code;


    public static SqlTypeEnum match(String code) {
        SqlTypeEnum[] values = SqlTypeEnum.values();
        for (int i = 0; i < values.length; i++) {
            SqlTypeEnum value = values[i];
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new RuntimeException(SystemMessage.ENUM_NOT_MATCH.getMessage());
    }


}
