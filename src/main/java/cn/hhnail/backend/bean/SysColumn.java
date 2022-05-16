package cn.hhnail.backend.bean;

import lombok.Data;
import lombok.ToString;

/**
 *
 */
@Data
@ToString
public class SysColumn {

        private String name;
        private String type;
        // 长度
        private Integer length;
        // 精度
        private String accuracy;
        // 是否必填
        private Integer nullable;
        // 是否虚拟
        private Integer virtual;
        // 键
        private String tableKey;
        // 是否自增
        private String autoIncrement;
        // 默认值
        private String defaultValue;
        // 备注
        private String remark;
}
