package com.yupexx.services.api.model.dto;

public class BusinessCatListDTO {
	
	public Integer parentCatId;
	public Integer catId;
	public Integer total;
	public String categoryName;
	
	public Integer getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(Integer parentCatId) {
		this.parentCatId = parentCatId;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "BusinessCatListDTO [catId=" + catId + ", total=" + total + ", categoryName=" + categoryName + "]";
	}
	
	
	

}
