package cn.hhnail.backend.enums;

public enum DateTemplate {

    yyyyMMdd("yyyy-MM-dd"),
    yyyyMMddhhmmss("yyyy-MM-dd hh:mm:ss");

    String templateString;

    DateTemplate(String templateString){
        this.templateString = templateString;
    }

    public String getTemplateString() {
        return templateString;
    }

    public void setTemplateString(String templateString) {
        this.templateString = templateString;
    }
}
