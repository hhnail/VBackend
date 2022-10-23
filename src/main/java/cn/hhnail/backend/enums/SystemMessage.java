package cn.hhnail.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemMessage {

    PRIMARY_TABLE_CAN_NOT_NULL("主表不得为空","primary table can not be null");

    private String message;

    private String englishMessage;

    public String getMessage(String language) {
        if (language == null || "zh".equals(language)){
            return this.message;
        }
        return this.englishMessage;
    }
}
