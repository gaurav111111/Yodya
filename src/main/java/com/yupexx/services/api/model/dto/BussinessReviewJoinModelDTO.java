package com.yupexx.services.api.model.dto;

import java.util.List;

import com.yupexx.services.api.model.business.BusinessReviewJoinModel;

public class BussinessReviewJoinModelDTO {
	
	public  String businessName;
	public BusinessReviewJoinModel businessReviewJoinModel;
	
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public BusinessReviewJoinModel getBusinessReviewJoinModel() {
		return businessReviewJoinModel;
	}
	public void setBusinessReviewJoinModel(BusinessReviewJoinModel businessReviewJoinModel) {
		this.businessReviewJoinModel = businessReviewJoinModel;
	}
	
	

}
