package com.ycbike.core.domain;

import java.io.Serializable;

public interface Identifiable extends Serializable {

	/**
	 * 获取主键 
	 * @return  返回组件值
	 */
	public String getUuid();
	
	/**
	 * 设置ID属性
	 * @param id
	 */
	public void setUuid(String uuid);
	
}
