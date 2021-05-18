package com.yupexx.services.api.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;





@Entity
@Table(name = "business_detail_mapping")
public class BusinessDetailMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Boolean status;
	private Long createdBy;
	private Long updatedBy=(long)1;
	
	@CreationTimestamp
	private Timestamp createdDate;
	
	private Timestamp updatedDate;
	private Integer businessId;
	private Integer categorydetailId;
	
	    @OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="categorydetailId",referencedColumnName="id",insertable = false,updatable = false)
		private ServicesCategoryDetails servicesCategoryDetails;

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

	
	
	
}
