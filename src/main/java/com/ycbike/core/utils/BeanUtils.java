package com.ycbike.core.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;

/** 
 * ��Bean���в�������ع��߷���
 * @author jiangbin
 *
 */
public class BeanUtils {
	/**
	 * ��Bean����ת����Map���󣬽����Ե�ֵΪnull��size=0������
	 * @param obj ����
	 * @return ����������Ϊnull�򷵻�size=0��map����
	 */
	public static Map<String, Object> toMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		BeanMap beanMap = new BeanMap(obj);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			String name = it.next();
			Object value = beanMap.get(name);
			// ת��ʱ�Ὣ����Ҳת�������ԣ��˴�ȥ��
			if (value != null && !name.equals("class")) {
				map.put(name, value);
			}
		}
		return map;
	}

	/**
	 * �÷��������������ж�������б�ת���ϲ�����һ��Map������ͬ�����ԣ������ɺ����滻ǰ��Ķ�������
	 * @param objs �����б�
	 * @return ����ֵΪnull�Ķ��󽫺��Ե�
	 */
	public static Map<String, Object> toMap(Object... objs) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object object : objs) {
			if (object != null) {
				map.putAll(toMap(object));
			}
		}
		return map;
	}

	/**
	 * ��ȡ�ӿڵķ������ͣ�����������򷵻�null
	 * @param clazz
	 * @return
	 */
	public static Class<?> getGenericClass(Class<?> clazz) {
		Type t = clazz.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			return ((Class<?>) p[0]);
		}
		return null;
	}
}