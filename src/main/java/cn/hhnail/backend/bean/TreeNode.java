package cn.hhnail.backend.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode {

	@ApiModelProperty(value = "唯一编码")
	private Integer id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "逻辑删除标记(0/1)")
	private Integer deleted;

	@ApiModelProperty(value = "叶子节点标记(0/1)")
	private Integer leafy;

	@ApiModelProperty(value = "所处层级")
	private Integer level;

	@ApiModelProperty(value = "父编号")
	private Integer pid;

	@ApiModelProperty(value = "子节点")
	// 不是数据库字段
	@TableField(exist = false)
	private List<TreeNode> children = new ArrayList<>();


}
