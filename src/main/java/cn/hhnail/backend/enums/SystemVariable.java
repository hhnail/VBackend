package cn.hhnail.backend.enums;

/**
 * 系统全局变量
 */
public enum SystemVariable {

    COLUMN_NAME_PREFIX("free_column_name_"),
    COLUMN_LABEL_PREFIX("free_column_label_");

    private String code;

    private SystemVariable(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // @Override
    // public String toString(){
    //     return this.getCode();
    // }
}
