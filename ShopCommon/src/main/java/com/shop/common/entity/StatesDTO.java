package com.shop.common.entity;

public class StatesDTO {
	
	private Integer stateId;
	private String stateName;
	
	
	public StatesDTO(Integer stateId, String stateName) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	

}
