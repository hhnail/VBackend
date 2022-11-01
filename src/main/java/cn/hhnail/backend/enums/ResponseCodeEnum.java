package cn.hhnail.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {
	
	SUCCESS(1,"操作成功"),
	FAIL(0,"服务器异常"),
	NOT_FOUND(404,"资源未找到"),
	NOT_AUTHED(403,"无权限，访问拒绝"),
	PARAM_INVAILD(400,"提交参数非法");
	
	private Integer code;
	private String msg;
}
