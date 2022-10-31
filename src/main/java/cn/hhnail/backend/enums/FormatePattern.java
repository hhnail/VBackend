package cn.hhnail.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormatePattern {

    yyyyMMdd_HHmmss("yyyy-MM-dd HH:mm:ss"),
    ;

    private String pattern;
}
