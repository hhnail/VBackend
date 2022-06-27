package cn.hhnail.backend.enums;

/**
 * 字段和值的比较方式
 * eg ：name = “zhangsan” id = 1 等...
 */
public enum CompareMethod implements BaseEnum{

    EQUAL("="),
    NOT_EQUAL("!="),
    BETWEEN("between"),
    IN("in"),
    NOT_IN("not in");

    private String value;

    CompareMethod(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(String value) {
        return this.value.equals(value);
    }

}
