package cn.hhnail.backend.enums;

import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * 系统表类型 枚举
 */
public enum TableType implements BaseEnum {

    // 单级码表
    SINGLE_CODE("SINGLE_CODE"),
    // 多级码表
    MULTILEVEL_CODE("MULTILEVEL_CODE");

    private String code;

    public static String mapDbColumn = "type";

    public String getCode() {
        return code;
    }

    TableType(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(String code) {
        return this.getCode().equals(code);
    }
}
