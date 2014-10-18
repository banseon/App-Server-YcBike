package com.ycbike.web.domain;
 

import com.ycbike.core.domain.Identifiable;

public class YcBikeUserLogin implements Identifiable{
	
	private String uuid;
	private String phone;
	private String name;
	private String password;
	private String createTime;
	private int flag;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return uuid;
	}
	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
