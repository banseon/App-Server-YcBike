package com.ycbike.web.domain;

import com.ycbike.core.domain.Identifiable;

public class YcBikeRouteNode implements Identifiable {
	private String uuid;
	private String refRouteId;
	private Integer nodeId;
	private String nodeName;
	private Integer parentId;
	private String coordinate;
	
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRefRouteId() {
		return refRouteId;
	}

	public void setRefRouteId(String refRouteId) {
		this.refRouteId = refRouteId;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	
}
