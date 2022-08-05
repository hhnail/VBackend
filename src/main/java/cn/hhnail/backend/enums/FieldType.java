package cn.hhnail.backend.enums;

public enum FieldType implements BaseEnum {

    INT("int"),
    VARCHAR("varchar");

    private String code;

    FieldType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(String code) {
        return this.getCode().equals(code);
    }
}
