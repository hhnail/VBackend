package cn.hhnail.backend.vo.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class UpdateModuleReqVO implements Serializable {

	private Integer id;
	@NotNull(message = "模块名称不得为空")
	private String name;
	@NotNull(message = "模块类型不得为空")
	private String type;

	private Integer level;

	private String routingAddress;

	private Integer orderId;

	private Integer pid;

	private Integer moduleId;
}
