package com.yupexx.services.api.model.dto;

import java.sql.Timestamp;
import java.util.List;

import com.yupexx.services.api.model.BusinessCategoryModel;
import com.yupexx.services.api.model.ServicesCategoryDetails;

public class BusinessCatVwDetailsModelDTO {
	
	private Integer id;
	private Integer parentCatId;
	private String categoryName;
	private Boolean status;
	private Long createdBy;
	private Long updatedBy;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private List<BusinessCatDetailsDTO> childData;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(Integer parentCatId) {
		this.parentCatId = parentCatId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public List<BusinessCatDetailsDTO> getChildData() {
		return childData;
	}
	public void setChildData(List<BusinessCatDetailsDTO> childData) {
		this.childData = childData;
	}
	
	
	


}
