package com.yupexx.services.api.request;

import java.util.List;

import javax.validation.constraints.NotNull;

public class BusinessReviewRequest {

	@NotNull(message = "businessId can not be null!")	
	private Integer businessId;
	@NotNull(message = "Description can not be null!")	
	private String description;
	
	private Integer serviceQuality;
	private Integer staffBehaviour;
	private Integer ambience;
	private Integer valueMoney;
	private Integer overall;
	
	private List<String> media;

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getServiceQuality() {
		return serviceQuality;
	}

	public void setServiceQuality(Integer serviceQuality) {
		this.serviceQuality = serviceQuality;
	}

	public Integer getStaffBehaviour() {
		return staffBehaviour;
	}

	public void setStaffBehaviour(Integer staffBehaviour) {
		this.staffBehaviour = staffBehaviour;
	}

	public Integer getAmbience() {
		return ambience;
	}

	public void setAmbience(Integer ambience) {
		this.ambience = ambience;
	}

	public Integer getValueMoney() {
		return valueMoney;
	}

	public void setValueMoney(Integer valueMoney) {
		this.valueMoney = valueMoney;
	}

	public Integer getOverall() {
		return overall;
	}

	public void setOverall(Integer overall) {
		this.overall = overall;
	}

	public List<String> getMedia() {
		return media;
	}

	public void setMedia(List<String> media) {
		this.media = media;
	}
	
	
	
}
