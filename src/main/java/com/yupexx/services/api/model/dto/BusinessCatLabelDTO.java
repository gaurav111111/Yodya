package com.yupexx.services.api.model.dto;

import java.sql.Timestamp;
import java.util.List;

import com.yupexx.bazaar.api.model.dto.CategoryDetailsDTO;
import com.yupexx.services.api.model.ServicesCategoryDetails;

public class BusinessCatLabelDTO {
	
	private Integer id;
	private String value;
	private Long createdBy = (long) 1;
	private Long updatedBy = (long) 1;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private Boolean status = true;
	private Integer categoryId;
	private List<ServicesCategoryDetails> detailsData;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public List<ServicesCategoryDetails> getDetailsData() {
		return detailsData;
	}
	public void setDetailsData(List<ServicesCategoryDetails> detailsData) {
		this.detailsData = detailsData;
	}
	
	

}
