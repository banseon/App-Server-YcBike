package com.ycbike.core.domain;

import java.io.Serializable;

public interface Identifiable extends Serializable {

	/**
	 * ��ȡ���� 
	 * @return  �������ֵ
	 */
	public String getUuid();
	
	/**
	 * ����ID����
	 * @param id
	 */
	public void setUuid(String uuid);
	
}
