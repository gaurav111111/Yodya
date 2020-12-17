package com.yupexx.services.api.request;

import javax.validation.constraints.NotNull;

public class BusinessReportRequest {

	@NotNull(message = "businessId can not be null!")	
	private Long businessId;
	@NotNull(message = "userMasterId can not be null!")
	private Long userMasterId;
	@NotNull(message = "reason can not be null!")
	private String reason;
	
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getUserMasterId() {
		return userMasterId;
	}
	public void setUserMasterId(Long userMasterId) {
		this.userMasterId = userMasterId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
