package com.yupexx.services.api.model.dto;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

public class BusinessMappingDTO {

	private Integer id;
	private Boolean status;
	private Long createdBy;
	private Long updatedBy=(long)1;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private Integer businessId;
	private Integer categorydetailId;
	private List<BusinessCatLabelDTO> label;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public Integer getCategorydetailId() {
		return categorydetailId;
	}
	public void setCategorydetailId(Integer categorydetailId) {
		this.categorydetailId = categorydetailId;
	}
	public List<BusinessCatLabelDTO> getLabel() {
		return label;
	}
	public void setLabel(List<BusinessCatLabelDTO> label) {
		this.label = label;
	}
	
	
	
	
	
}
