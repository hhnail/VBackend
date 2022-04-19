package cn.hhnail.backend.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TreeNode {

	@ApiModelProperty(value = "唯一编码")
	private Integer id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "逻辑删除标记")
	private Integer deleted;

	@ApiModelProperty(value = "父编号")
	private Integer pid;


}
