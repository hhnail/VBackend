package cn.hhnail.backend.vo.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AntdTableColumn implements Serializable {

    private String title; // 字段中文名称
    private String dataIndex; // 字段（在UI组件中）数据匹配字段的唯一标识
    private String columnCode; // 真实的数据库字段名
    private String key; // 字段（在UI组件中）用于Diffing算法的唯一标识
    private Integer width;  // 字段（在UI组件中）展示的列宽度

}
