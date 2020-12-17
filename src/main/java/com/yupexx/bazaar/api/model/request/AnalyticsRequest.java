package com.yupexx.bazaar.api.model.request;

import javax.validation.constraints.NotNull;

public class AnalyticsRequest {

	@NotNull(message = "Ad can not be null!")
	private Integer adId;
	@NotNull(message = "User can not be null!")
	private Long userMasterId;
	@NotNull(message = "Type can not be null!")
	private String type;
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Long getUserMasterId() {
		return userMasterId;
	}
	public void setUserMasterId(Long userMasterId) {
		this.userMasterId = userMasterId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
