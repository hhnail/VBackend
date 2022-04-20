package cn.hhnail.backend.vo.response;

import cn.hhnail.backend.bean.TreeNode;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNodeRespVO {
	@ApiModelProperty(value = "唯一编码")
	private Integer key;

	@ApiModelProperty(value = "名称")
	private String title;

	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "叶子节点标记(0/1)")
	private Integer leafy;

	@ApiModelProperty(value = "所处层级")
	private Integer level;

	@ApiModelProperty(value = "子节点")
	private List<TreeNode> children = new ArrayList<>();
}
