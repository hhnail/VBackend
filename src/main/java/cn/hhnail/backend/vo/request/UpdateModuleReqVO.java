package cn.hhnail.backend.vo.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateModuleReqVO {

	private String name;

	private String type;

	private Integer level;

	private String routingAddress;

	private Integer pid;
}
