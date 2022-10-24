package cn.hhnail.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 全参构造
public enum SystemMessage {

    PRIMARY_TABLE_CAN_NOT_NULL("主表不得为空", 500, "primary table can not be null"),
    ADD_FAIL("新增失败", 500, "add failed"),
    EDIT_FAIL("编辑失败", 500, "edit failed"),
    DELETE_FAIL("删除失败", 500, "delete failed"),
    ADD_SUCCESS("新增成功", 500, "add success"),
    EDIT_SUCCESS("编辑成功", 500, "edit failed"),
    DELETE_SUCCESS("删除成功", 500, "delete failed"),
    ;

    private String message;

    private Integer code;

    private String englishMessage;

    public String getMessage(String language) {
        if (language == null || "zh".equals(language)) {
            return this.message;
        }
        return this.englishMessage;
    }
}
