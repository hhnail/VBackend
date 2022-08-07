package cn.hhnail.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 系统字段
 */
@Data
@ToString
public class SysColumn implements Serializable {

        @TableId(type = IdType.AUTO)
        @ApiModelProperty(value = "编号")
        private String id;
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
        @ApiModelProperty(value = "是否可见")
        private Integer visible;

        @ApiModelProperty(value = "删除标记")
        private Integer deleted;
        // 是否虚拟
        @TableField(exist = false)
        private Integer virtual;
        // 键
        private String tableKey;
        // 是否自增
        private String autoIncrement;
        // 默认值
        private String defaultValue;
        // 备注
        private String remark;
        @ApiModelProperty(value = "所属表编号")
        private String sysTableId;
        @TableField(exist = false)
        private String sysTableName;
}
