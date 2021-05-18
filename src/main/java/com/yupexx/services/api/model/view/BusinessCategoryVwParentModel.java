package com.yupexx.services.api.model.view;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yupexx.services.api.model.BusinessCategoryModel;

@Entity
@Table(name = "business_category_master")
@Where(clause = "parentCatId is null")
public class BusinessCategoryVwParentModel {

	@Id
	private Integer id;

	private Integer parentCatId;
	private String categoryName;

	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")

	private Boolean status;

	private Long createdBy;

	private Long updatedBy;

	@CreationTimestamp
	private Timestamp createdDate;

	@UpdateTimestamp
	private Timestamp updatedDate;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="parentCatId",referencedColumnName="id")
	@Where(clause = "parentCatId is not null")
	private List<BusinessCategoryModel> data;

	
	
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

	public List<BusinessCategoryModel> getData() {
		return data;
	}

	public void setData(List<BusinessCategoryModel> data) {
		this.data = data;
	}
	
}
