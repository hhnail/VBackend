package cn.hhnail.backend.bean;

import cn.hhnail.backend.vo.response.AntdTableColumn;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FreeReport implements Serializable {

    @ApiModelProperty(value = "ID")
    // @TableField(exist = false)
    private Integer id;

    @ApiModelProperty(value = "名称")
    @TableField(exist = false)
    private String name;

    @ApiModelProperty(value = "模块ID")
    @TableField(exist = false)
    private Integer moduleId;

    @ApiModelProperty(value = "描述")
    @TableField(exist = false)
    private String description;

    @ApiModelProperty(value = "报表SQL")
    @TableField(exist = false)
    private String reportSql;

    @ApiModelProperty(value = "展示字段")
    // @TableField(exist = false)
    private String columnsView;

    @ApiModelProperty(value = "请求字段")
    @TableField(exist = false)
    private String columnsQuery;

    @ApiModelProperty(value = "删除标记")
    @TableField(exist = false)
    private Integer deleted;
}
