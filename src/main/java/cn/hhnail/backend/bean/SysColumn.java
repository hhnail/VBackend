package cn.hhnail.backend.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysColumn implements Serializable {

	private Integer id;

	private String name;

	private String SysTableId;

	private String SysTableName;

	private String type;

	private Integer length;

}
