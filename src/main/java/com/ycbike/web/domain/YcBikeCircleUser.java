package com.ycbike.web.domain;

import com.ycbike.core.domain.Identifiable;

public class YcBikeCircleUser implements Identifiable {

	private String uuid;
	private String refCircleId;
	private String refUserId;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRefCircleId() {
		return refCircleId;
	}

	public void setRefCircleId(String refCircleId) {
		this.refCircleId = refCircleId;
	}

	public String getRefUserId() {
		return refUserId;
	}

	public void setRefUserId(String refUserId) {
		this.refUserId = refUserId;
	}

	
	
}
