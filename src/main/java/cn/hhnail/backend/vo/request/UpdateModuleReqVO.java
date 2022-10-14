package cn.hhnail.backend.vo.request;

import lombok.Data;
import lombok.ToString;

/**
 * 新增、更新模块信息VO
 */
@Data
@ToString
public class UpdateModuleReqVO {

	private Integer id;

	private String name;

	private String type;

	private Integer level;

	private String routingAddress;

	private Integer orderId;

	private Integer moduleId;

	private Integer pid;
}
