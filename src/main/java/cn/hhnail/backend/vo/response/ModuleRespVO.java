package cn.hhnail.backend.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModuleRespVO {

	private Integer key;

	private String name;

	private String routingAddress;

	private String parentRoutingAddress;

	private Integer level;

	private Integer pid;

	private Integer moduleId;

	private List<ModuleRespVO> children = null;

}
