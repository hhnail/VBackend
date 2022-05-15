package cn.hhnail.backend.util;

import java.util.Collection;

/**
 * 集合工具类
 */
public class MyCollectionUtils {

	/**
	 * 是否只有一个元素
	 */
	public static boolean hasOneEle(Collection collection) {
		if(collection != null && collection.size() == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 是否为空
	 */
	public static boolean isEmpty(Collection collection) {
		if(collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}
}
