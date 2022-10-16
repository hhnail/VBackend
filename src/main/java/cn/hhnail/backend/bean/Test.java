package cn.hhnail.backend.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class Test {

    @ApiModelProperty(value = "唯一编码")
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "生日")
    private Date birthday;
    @ApiModelProperty(value = "删除标记")
    private Integer deleted;


}
