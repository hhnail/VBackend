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

	private Integer level;

	private Integer pid;

	private List<ModuleRespVO> children = null;

}
