package cn.hhnail.backend.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode {

	@ApiModelProperty(value = "唯一编码")
	// 自增主键
	@TableId(type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value = "名称")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String name;

	@ApiModelProperty(value = "类型")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String type;

	@ApiModelProperty(value = "逻辑删除标记(0/1)")
	private Integer deleted;

	@ApiModelProperty(value = "叶子节点标记(0/1)")
	private Integer leafy;

	@ApiModelProperty(value = "所处层级")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Integer level;

	@ApiModelProperty(value = "父编号")
	@TableField(fill = FieldFill.INSERT)
	private Integer pid;

	@ApiModelProperty(value = "路由地址")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String routingAddress;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String orderId;

	@ApiModelProperty(value = "子节点集合")
	// 不是数据库字段
	@TableField(exist = false)
	private List<TreeNode> children = new ArrayList<>();


}
