package cn.hhnail.backend.enums;

public enum ConditionType implements BaseEnum{

    AND("and"),
    OR("or");

    private String value;

    ConditionType(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(String value) {
        return this.value.equals(value);
    }
}
