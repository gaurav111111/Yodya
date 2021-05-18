package com.yupexx.services.api.model.dto;

import java.util.List;

import com.yupexx.services.api.model.BusinessDetailMapping;
import com.yupexx.services.api.model.business.BusinessJoinModel;

public class BusinessJoinModelDTO {

	private BusinessJoinModel businessJoinModel;
	private List<BusinessDetailMapping> businessDetailMapping;
	public BusinessJoinModel getBusinessJoinModel() {
		return businessJoinModel;
	}
	public void setBusinessJoinModel(BusinessJoinModel businessJoinModel) {
		this.businessJoinModel = businessJoinModel;
	}
	public List<BusinessDetailMapping> getBusinessDetailMapping() {
		return businessDetailMapping;
	}
	public void setBusinessDetailMapping(List<BusinessDetailMapping> businessDetailMapping) {
		this.businessDetailMapping = businessDetailMapping;
	}
	
	
}
