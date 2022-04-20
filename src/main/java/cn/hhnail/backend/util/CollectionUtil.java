package cn.hhnail.backend.util;

import java.util.Collection;

/**
 * 集合工具类
 */
public class CollectionUtil {

	/**
	 * 是否只有一个元素
	 * @param collection
	 * @return
	 */
	public static boolean hasOneElement(Collection collection) {
		if(collection != null && collection.size() == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection collection) {
		if(collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}
}
