package com.ycbike.core.domain;

import java.io.Serializable;

public interface Identifiable extends Serializable {

	/**
	 * ��ȡ���� 
	 * @return  �������ֵ
	 */
	public String getId();
	
	/**
	 * ����ID����
	 * @param id
	 */
	public void setId(String id);
	
}
