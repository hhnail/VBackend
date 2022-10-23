package cn.hhnail.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统全局变量
 */
@Getter
@AllArgsConstructor
public enum SystemVariable {

    COLUMN_NAME_PREFIX("free_column_name_"),
    COLUMN_LABEL_PREFIX("free_column_label_"),
    COLUMN_UNITE_PREFIX("v_"),
    ;

    private String code;

}
