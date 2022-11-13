package cn.hhnail.backend.vo.response;

import cn.hhnail.backend.enums.ResponseCodeEnum;
import lombok.Data;


/**
 * 应用统一返回结果数据封装类
 * @author Administrator
 * @param <T> 返回结果数据类型
 */
@Data
public class AppResponse<T> {

	private Integer code;
	private String msg;
	private T data;
	

	/**
	 * 快速响应成功
	 * @param data
	 * @return
	 */
	public static<T> AppResponse<T> ok(T data){
		AppResponse<T> resp = new AppResponse<T>();
		resp.setCode(ResponseCodeEnum.SUCCESS.getCode());
		resp.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
		resp.setData(data);
		return resp;
	}
	
	/**
	 * 快速响应失败（有data）
	 */
	public static<T> AppResponse<T> fail(T data){
		AppResponse<T> resp = new AppResponse<T>();
		resp.setCode(ResponseCodeEnum.FAIL.getCode());
		resp.setMsg(ResponseCodeEnum.FAIL.getMsg());
		resp.setData(data);
		return resp;
	}

	/**
	 * 快速响应失败（无data）
	 */
	public static<T> AppResponse<T> fail(){
		AppResponse<T> resp = new AppResponse<T>();
		resp.setCode(ResponseCodeEnum.FAIL.getCode());
		resp.setMsg(ResponseCodeEnum.FAIL.getMsg());
		return resp;
	}
}
