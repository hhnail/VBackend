package cn.hhnail.backend.enums;

/**
 * 枚举类
 * 百度翻译API
 * 语种
 * @Author Hhnail
 */
public enum Languages {

    CHINESE("zh", "中文"),
    ENGLISH("en", "英文"),
    CANTONESE("yue", "粤语"),
    JAPANESE("jp", "日语");

    private String code;

    private String name;

    Languages(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode(){
        return code;
    }

    public boolean equals(String code){
        return this.getCode().equals(code);
    }


}
