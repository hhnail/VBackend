package cn.hhnail.backend.util;

/**
 * 加密工具类
 *
 * 单例模式
 */
public class EncryptUtil {

	private final static EncryptUtil INSTANCE = new EncryptUtil();

	private EncryptUtil(){

	}

	public EncryptUtil getInstance(){
		return INSTANCE;
	}
}
