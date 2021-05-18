package com.yupexx.bazaar.api.model.dto;

import java.sql.Timestamp;
import java.util.List;

import com.yupexx.bazaar.api.model.CategoryDetailsModel;



public class BazaarLabelDto {
private Integer id;
	
	private String value;
	
	   private Long createdBy=(long)1;
		
		private Long updatedBy=(long)1;
		

		private Timestamp createdDate;
		
		
		private Timestamp updatedDate;
		
		
		private Boolean status=true;
		
	    private Integer categoryId;
	    
	    List<CategoryDetailsModel> categoryDetailList;
	    
	    

		

		public List<CategoryDetailsModel> getCategoryDetailList() {
			return categoryDetailList;
		}

		public void setCategoryDetailList(List<CategoryDetailsModel> categoryDetailList) {
			this.categoryDetailList = categoryDetailList;
		}

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

	    
	    
}
