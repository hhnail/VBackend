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
    private String name;
    @ApiModelProperty(value = "模块ID")
    private Integer moduleId;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "报表SQL")
    private String reportSql;
    @ApiModelProperty(value = "展示字段")
    private String columnsView;
    @ApiModelProperty(value = "请求字段")
    private String columnsQuery;
    @ApiModelProperty(value = "删除标记")
    private Integer deleted;
}
