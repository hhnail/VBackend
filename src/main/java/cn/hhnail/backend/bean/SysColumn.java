package cn.hhnail.backend.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 */
@Data
@ToString
public class SysColumn implements Serializable {

        @ApiModelProperty(value = "英文名")
        private String name;
        @ApiModelProperty(value = "中文名")
        private String label;
        @ApiModelProperty(value = "字段类型（varchar、int等）")
        private String type;
        @ApiModelProperty(value = "长度")
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
