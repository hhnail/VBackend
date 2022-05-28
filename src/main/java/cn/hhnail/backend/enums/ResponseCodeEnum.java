package cn.hhnail.backend.enums;

public enum ResponseCodeEnum {
	
	SUCCESS(0,"操作成功"),
	FAIL(1,"服务器异常"),
	NOT_FOUND(404,"资源未找到"),
	NOT_AUTHED(403,"无权限，访问拒绝"),
	PARAM_INVAILD(400,"提交参数非法");
	
	private Integer code;
	private String msg;
	
	private ResponseCodeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

	public boolean equals(Integer code){
		return this.getCode().equals(code);
	}

}
