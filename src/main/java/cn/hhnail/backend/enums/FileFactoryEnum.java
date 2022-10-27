package cn.hhnail.backend.enums;

import cn.hhnail.backend.service.ExcelService;
import cn.hhnail.backend.service.FileService;
import cn.hhnail.backend.util.SpringContextUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum FileFactoryEnum {

    EXCEL("excel", Arrays.asList(".xls", ".xlsx"), ExcelService.class);

    private String type;
    private List<String> suffix;
    private Class<?> service;

    public static FileService getService(String type) {
        for (FileFactoryEnum fileEnum : FileFactoryEnum.values()) {
            if (fileEnum.getType().equals(type)) {
                return (FileService) SpringContextUtil.getBean(fileEnum.getService());
            }
        }
        throw new RuntimeException("获取service失败");
    }
}
