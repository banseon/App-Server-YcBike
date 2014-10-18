package com.ycbike.web.domain;

import com.ycbike.core.domain.Identifiable;

public class YcBikeCircle implements Identifiable {

	private String uuid;
	private String circleName;
	private String circleDesc;
	private Integer flag;
	private String adminId;
	private String superAdminId;
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getCircleDesc() {
		return circleDesc;
	}

	public void setCircleDesc(String circleDesc) {
		this.circleDesc = circleDesc;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getSuperAdminId() {
		return superAdminId;
	}

	public void setSuperAdminId(String superAdminId) {
		this.superAdminId = superAdminId;
	}

	
	
	
}
